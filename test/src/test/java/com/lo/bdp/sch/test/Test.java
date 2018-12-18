package com.lo.bdp.sch.test;

import com.lo.bdp.sch.service.pojo.entity.User;
import com.lo.bdp.sch.service.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml","classpath:spring-orm.xml"})
public class Test {
    //@Autowired
    UserService userService;

    @org.junit.Test
    public void test(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        //userService = ac.getBean(UserService.class);
        final List<User> all = userService.findAll();
        //System.out.println(userDao.find(1));
    }
}
