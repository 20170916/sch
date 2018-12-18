import com.lo.bdp.sch.master.core.MasterContext;
import com.lo.bdp.sch.master.service.SchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        SchService taskLifecycleService = ac.getBean("taskLifecycleService",SchService.class);
        masterContext.addService(taskLifecycleService);
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
