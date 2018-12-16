import com.lo.core.MasterContext;
import com.lo.service.HAService;
import com.lo.service.SchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//@Component
public class Master {
    static final Logger logger = LoggerFactory.getLogger(Master.class);
    private static ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");;
    private SchService haService=ac.getBean("hAService",SchService.class);
    private MasterContext masterContext=ac.getBean(MasterContext.class);
    public static void main(String[] args) {
        //Master master = ac.getBean(Master.class);
        Master master = new Master();
        SchService haService=master.haService;
        MasterContext masterContext=master.masterContext;
        master.startService();
        while (true){

        }
    }


    public void startService(){
        haService.startService();
    }

}
