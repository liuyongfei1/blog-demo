package com.fullstackboy.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ProfilingAspect {

    // 定义切点
    @Pointcut("execution(* com.fullstackboy.aspectj.Account.*(..))")
    public void modelLayer() {}

    /**
     * 在切点执行前后采取的行为，这里是记录方法调用的时间
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("modelLayer()")
    public Object logProfile(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        System.out.println("[ProfilingAspect] 方法：【" + joinPoint.getSignature()
                 + "】结束，耗时：" + (System.currentTimeMillis() - startTime));

        return result;
    }
}