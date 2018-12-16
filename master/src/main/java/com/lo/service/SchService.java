package com.lo.service;

public interface SchService {
    /**
     * 用来启动服务的入口。
     * <p>aop会对启动入口前后做日志记录</p>
     */
    void startService();
}
