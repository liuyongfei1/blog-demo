package com.fullstackboy.springdemo.ioc.bean;

import org.aspectj.lang.annotation.*;

/**
 * 日志切面类
 *
 * @author Liuyongfei
 * @date 2022/1/14 22:24
 */
@Aspect
public class LogAspect {

    @Pointcut("execution(public int com.fullstackboy.springdemo.ioc.bean.Calculation.*(..))")
    public void pointCut() {};

    @Before("pointCut()")
    public void logStart() {
        System.out.println("计算开始前......");
    }

    @After("pointCut()")
    public void logEnd() {
        System.out.println("计算结束......");
    }

    @AfterReturning("pointCut()")
    public void logReturn() {
        System.out.println("计算方法返回值......");
    }

    @AfterThrowing("pointCut()")
    public void logException() {
        System.out.println("计算发生异常......");
    }
}
