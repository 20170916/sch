package com.lo.core;

import com.lo.pojo.IpPort;
import org.springframework.stereotype.Component;

@Component
public class MasterContext {
    private String activeMasterIp;

    public String getActiveMasterIp() {
        return activeMasterIp;
    }

    public void setActiveMasterIp(String activeMasterIp) {
        this.activeMasterIp = activeMasterIp;
    }

}
