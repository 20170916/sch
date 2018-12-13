package com.lo.service;

import com.lo.core.MasterContext;
import com.lo.core.util.SysUtil;
import com.lo.pojo.IpPort;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkException;
import org.I0Itec.zkclient.exception.ZkInterruptedException;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class HAService {
    static final Logger logger = LoggerFactory.getLogger(HAService.class);
    private static final String MASTER_PATH="/master/active";
    private static final String SERVERS_PATH="/master/server";
    @Autowired
    private MasterContext masterContext;
    @Autowired
    private ZkClient zkclient;
    private IZkDataListener masterPathListener;
    private IZkStateListener zkStateListener;
    private ScheduledExecutorService delayExector = new ScheduledThreadPoolExecutor(1);
    private String localIp;

    private HAService(){
        localIp = SysUtil.getIP()+" "+System.currentTimeMillis();
        masterPathListener = new IZkDataListener() {

            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                //ignore it
            }

            /**
             * 节点被删除时（长连接超时，或active节点宕机），active状态master立刻推荐自己，standby状态master延迟5秒后推荐自己。
             *
             * @param dataPath
             * @throws Exception
             */
            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                String activeMaster = masterContext.getActiveMasterIp();
                if (activeMaster != null && activeMaster.equals(localIp)) {
                    recommendSelf();
                } else {
                    delayExector.schedule(new Runnable() {
                        @Override
                        public void run() {
                            recommendSelf();
                        }
                    }, 5, TimeUnit.SECONDS);

                }
            }
        };

        zkStateListener = new IZkStateListener() {
            @Override
            public void handleStateChanged(Watcher.Event.KeeperState state) throws Exception {
                //ignore it
            }

            /**
             * zk连接断开时，推荐自己
             * @throws Exception
             */
            @Override
            public void handleNewSession() throws Exception {
                registerMasterService();
            }

            @Override
            public void handleSessionEstablishmentError(Throwable error) throws Exception {
                //ignore it
            }
        };
    }

    /**
     * 通过zk进行选举。
     */
    public void elect(){
        registerMasterService();
        subscribeChanges();
        recommendSelf();
    }

    /**
     * 注册监听
     */
    private void subscribeChanges(){
        zkclient.subscribeDataChanges(MASTER_PATH, masterPathListener);
        zkclient.subscribeStateChanges(zkStateListener);
    }

    /**
     * 所有master向/sch/master目录注册临时节点，存自己的ip，来推荐自己；
     */
    private void recommendSelf(){
        try {
            zkclient.create(MASTER_PATH, localIp.getBytes(), CreateMode.EPHEMERAL);
            masterContext.setActiveMasterIp(localIp);
            logger.info(localIp+ " is master");

            delayExector.schedule(new Runnable() {
                @Override
                public void run() {
                    if (checkMaster()) {
                        releaseMaster();
                    }
                }
            }, 5, TimeUnit.SECONDS);

        } catch (ZkNodeExistsException e) {
            byte[] data = zkclient.readData(MASTER_PATH, true);
            String activeMasterIp = data == null ? null : new String(data);
            if (activeMasterIp == null) {
                recommendSelf();
            } else {
                masterContext.setActiveMasterIp(activeMasterIp);
            }
        } catch (Exception e) {
            //ignore it ,do nothing
        }
    }

    /**
     * 所有master启动时注册自己，声明自己随时可以提供服务。
     */
    private void registerMasterService(){
        String mePath = SERVERS_PATH.concat("/").concat(localIp);
        try {
            zkclient.createEphemeral(mePath, mePath.getBytes());
        } catch (ZkNoNodeException e) {
            zkclient.createPersistent(SERVERS_PATH, true);
            registerMasterService();
        } catch (Exception e) {
            logger.error("register master server error", e);
        }
    }

    private boolean checkMaster() {
        try {
            String activeMasterIp = zkclient.readData(MASTER_PATH);
            masterContext.setActiveMasterIp(activeMasterIp);
            return masterContext.getActiveMasterIp().equals(localIp);
        } catch (ZkInterruptedException e) {// 操作中断异常处理
            return checkMaster();
        } catch (ZkException e) {
            return false;
        }
    }

    private void releaseMaster() {
        if (checkMaster()) {
            zkclient.delete(MASTER_PATH);
        }
    }

}
