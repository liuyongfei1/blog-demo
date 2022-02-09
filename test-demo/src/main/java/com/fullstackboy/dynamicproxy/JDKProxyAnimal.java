package com.fullstackboy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * JDK动态代理类
 *
 * @author Liuyongfei
 * @date 2022/1/23 17:30
 */
public class JDKProxyAnimal {
//    public static Object getProxy(Object target) {
//        Object proxy = Proxy.newProxyInstance(
//                // 指定目标类的类加载
//                target.getClass().getClassLoader(),
//                // 代理需要实现的接口，可指定多个 =》与目标对象实现同样的接口
//                target.getClass().getInterfaces(),
//                // 代理对象处理器，即InvocationHandler实现类
//                new TargetInvoker(target)
//        );
//        return proxy;
//    }

    public static Object getProxy(Object target) {
        InvocationHandler handler = (proxy, method, args) -> {
            System.out.println("test jdk 代理执行前...");
            method.invoke(target);
            System.out.println("test jdk 代理执行后...");
            // 添加排除方法
            return "你被代理了 " + method.getName();
        };

        Object proxy = Proxy.newProxyInstance(
                // 指定目标类的类加载
                target.getClass().getClassLoader(),
                // 代理需要实现的接口，可指定多个 =》与目标对象实现同样的接口
                target.getClass().getInterfaces(),
                // 代理对象处理器，即InvocationHandler实现类
                handler
        );
        return proxy;
    }
}
