package com.lo.service;

import com.lo.core.MasterContext;
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
