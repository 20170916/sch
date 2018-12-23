package com.lo.bdp.sch.master.util;

import com.lo.bdp.sch.master.config.SchService;
import com.lo.bdp.sch.master.core.util.ClassUtil;
import org.junit.Test;

import java.util.Set;

public class ClassUtilTest {
    @Test
    public void test(){
        System.out.println(1);
        Set<Class<?>> classSet = ClassUtil.getClassSet("com.lo.bdp.sch.master.service");
        for (Class<?> aClass : classSet) {
            //System.out.println(aClass);
            if(aClass.isAnnotationPresent(SchService.class)){
                System.out.println(aClass);
            }
        }
    }
}
