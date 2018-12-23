package com.lo.bdp.sch.master.core.util;

public class StringUtil {
    //首字母转小写
    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
