package com.fullstackboy.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * 动态代理类
 *
 * @author Liuyongfei
 * @date 2022/1/23 17:30
 */
public class DynamicProxyAnimal {
    public static Object getProxy(Object target) {
        Object proxy = Proxy.newProxyInstance(
                // 指定目标类的类加载
                target.getClass().getClassLoader(),
                // 代理需要实现的接口，可指定多个 =》与目标对象实现同样的接口
                target.getClass().getInterfaces(),
                // 代理对象处理器，即InvocationHandler实现类
                new TargetInvoker(target)
        );
        return proxy;
    }
}
