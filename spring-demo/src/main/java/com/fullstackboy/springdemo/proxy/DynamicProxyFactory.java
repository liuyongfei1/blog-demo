package com.fullstackboy.springdemo.proxy;

import java.lang.reflect.Proxy;

/**
 * 动态代理类工厂
 *
 * @author Liuyongfei
 * @date 2021/7/31 12:35
 */
public class DynamicProxyFactory {
    public static Object getProxy(Object object) {
        DynaProxyHandler handler = new DynaProxyHandler();
        handler.setTarget(object);

        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),handler);
    }
}
