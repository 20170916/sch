import org.I0Itec.zkclient.ZkClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:spring-zk.xml"})
public class ZKClientTest {
    private static final String ACTIVE_MASTER_PATH="/master/active";
    @Autowired
    private ZkClient zkclient;

    @Test
    public void test(){
        byte[] activeMasterIp = zkclient.readData(ACTIVE_MASTER_PATH,true);
        System.out.println(new String(activeMasterIp));
    }
}
