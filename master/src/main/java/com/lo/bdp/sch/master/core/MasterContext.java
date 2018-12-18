package com.lo.bdp.sch.master.core;

import com.lo.bdp.sch.master.service.SchService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MasterContext {
    public static enum State{
        ACTIVE,STANDBY
    }
    private String activeMasterIp;
    private State state;
    private List<SchService> schServices=new ArrayList<>();

    public List<SchService> getSchServices() {
        return schServices;
    }

    public void addService(SchService schService){
        schServices.add(schService);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isActive(){
        return State.ACTIVE.equals(this.state);
    }

    public String getActiveMasterIp() {
        return activeMasterIp;
    }

    public void setActiveMasterIp(String activeMasterIp) {
        this.activeMasterIp = activeMasterIp;
    }

}
