import com.lo.bdp.sch.master.core.MasterContext;
import com.lo.bdp.sch.master.core.util.ClassUtil;
import com.lo.bdp.sch.master.core.util.StringUtil;
import com.lo.bdp.sch.master.service.SchService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Set;

//@Component
public class Master {
    static final Logger logger = LoggerFactory.getLogger(Master.class);
    private static ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");;
    private SchService haService=ac.getBean("hAService",SchService.class);
    private MasterContext masterContext=ac.getBean(MasterContext.class);
    public static void main(String[] args) {
        //Master master = ac.getBean(Master.class);
        Master master = new Master();
        master.registerService();
        master.startService();

    }

    /**
     * 注册服务。在haService启动成功成为active时，回调start方法。
     * 可通过注解或配置文件方式采集服务；
     */
    private void registerService(){
        /*SchService taskLifecycleService = ac.getBean("taskLifecycleService",SchService.class);
        masterContext.addService(taskLifecycleService);*/
        Set<Class<?>> classSet = ClassUtil.getClassSet("com.lo.bdp.sch.master.service");
        for (Class<?> aClass : classSet) {
            //System.out.println(aClass);
            if(aClass.isAnnotationPresent(com.lo.bdp.sch.master.config.SchService.class)){
                try {
                    String simpleClassName=aClass.getSimpleName();
                    simpleClassName=StringUtil.toLowerCaseFirstOne(simpleClassName);
                    SchService schService =  ac.getBean(simpleClassName,SchService.class);
                    masterContext.addService(schService);
                    schService.registerSelf(schService);
                    logger.info("register {} server success",aClass.getName());
                } catch (Exception e) {
                    logger.error("{} register error",aClass.getName(),e);
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 启动haServer，成为active的master去启动其他注册的服务。
     *
     */
    private void startService(){
        haService.startService();
        /*Thread thread=new Thread(() -> {
           while (true){
              *//* System.out.println("--");
               logger.info("-----");*//*
           }
        });
        thread.start();*/
    }

}
