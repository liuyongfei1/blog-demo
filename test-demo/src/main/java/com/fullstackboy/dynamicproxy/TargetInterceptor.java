package com.fullstackboy.dynamicproxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 方法拦截器
 * 通过方法拦截接口调用目标类的方法
 * @author Liuyongfei
 * @date 2022/1/23 20:11
 */
public class TargetInterceptor implements MethodInterceptor {

    /**
     *
     * @param o 代理类对象
     * @param method 当前被代理拦截的方法
     * @param args 当前被代理拦截的方法的参数
     * @param proxy 代理类对应目标类的代理方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("CGLIB 调用前");
        proxy.invokeSuper(o, args);
        System.out.println("CGLIB 调用后");
        return null;
    }
}
