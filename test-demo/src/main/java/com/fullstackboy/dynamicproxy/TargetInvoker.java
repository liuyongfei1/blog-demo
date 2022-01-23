package com.fullstackboy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理对象逻辑处理器
 *
 * @author Liuyongfei
 * @date 2022/1/23 17:21
 */
public class TargetInvoker implements InvocationHandler {

    /**
     * 代理中持有的目标类
     */
    private Object target;

    public TargetInvoker(Object target) {
        this.target = target;
    }

    /**
     *
     * @param proxy 代理对象
     * @param method 执行目标类的方法
     * @param args 执行目标类的方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk 代理执行前...");
        method.invoke(target);
        System.out.println("jdk 代理执行后...");
        return null;
    }
}
