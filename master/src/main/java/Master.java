import com.lo.service.HAService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Master {
    static final Logger logger = LoggerFactory.getLogger(Master.class);
    private ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");;
    @Autowired
    private HAService haService=ac.getBean(HAService.class);
    public static void main(String[] args) {
        Master master = new Master();
        master.startService();
        while (true){

        }
    }


    public void startService(){
        haService.elect();
    }

}
