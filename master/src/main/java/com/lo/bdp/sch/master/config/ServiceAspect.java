package com.lo.bdp.sch.master.config;

import com.lo.bdp.sch.master.service.HAService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {
    static final Logger logger = LoggerFactory.getLogger(ServiceAspect.class);
    //@Around("execution(* startService*(..))")
    @Around("execution(* com.lo.bdp.sch.master.service..*.startService*(..))")
    public Object before(ProceedingJoinPoint proceedingJoinPoint){
        Object ret=null;
        String serviceName = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        try{
            logger.info("-----------start {}-----------",serviceName);

            ret = proceedingJoinPoint.proceed();
        }catch (Throwable throwable){

            logger.info("-----------start {} error-----------",serviceName);
            throw new RuntimeException(throwable);
        }
        logger.info("-----------start {} succeed-----------",serviceName);
        return ret;
    }
}
