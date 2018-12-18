package com.lo.bdp.sch.master.service;

import com.lo.bdp.sch.master.core.MasterContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskLifecycleService implements SchService{
    @Autowired
    private MasterContext masterContext;
    @Override
    public void startService() {
        /*while (true){

        }*/
    }

    public void register(){
        masterContext.addService(this);
    }
}
