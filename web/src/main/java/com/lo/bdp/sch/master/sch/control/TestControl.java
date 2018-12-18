package com.lo.bdp.sch.master.sch.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test")
public class TestControl {

    @RequestMapping("test")
    public String test(){
        return "test";
    }
}
