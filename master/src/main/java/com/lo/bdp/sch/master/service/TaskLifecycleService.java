package com.lo.bdp.sch.master.service;

import com.lo.bdp.sch.master.core.MasterContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@com.lo.bdp.sch.master.config.SchService
public class TaskLifecycleService implements SchService{
    @Autowired
    private MasterContext masterContext;
    @Override
    public void startService() {
        System.out.println("TaskLifecycleService work");
        while (true){

        }
    }

    @Override
    public void registerSelf(SchService schService) {

        //masterContext.addService(this);//this是被代理类对象
        masterContext.addService(schService);//schService是spring生成的代理对象
    }
}
