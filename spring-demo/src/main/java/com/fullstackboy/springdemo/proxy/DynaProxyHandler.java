package com.fullstackboy.springdemo.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 创建处理器，用于增强逻辑
 *
 * @author Liuyongfei
 * @date 2021/7/31 12:36
 */
public class DynaProxyHandler implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(DynaProxyHandler.class);

    /**
     * 被代理对象
     */
    private Object target;
    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("sleep")) {
            logger.info("sleep方法开始执行时间:" + new Date());
        } else {
            logger.info("eating方法开始执行时间:" + new Date());
        }

        Object result = method.invoke(target, args);

        if (method.getName().equals("sleep")) {
            logger.info("sleep方法结束执行时间:" + new Date());
        } else {
            logger.info("eating方法结束执行时间:" + new Date());
        }
        return result;
    }
}
