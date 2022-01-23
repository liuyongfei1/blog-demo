package com.fullstackboy.dynamicproxy;

import org.springframework.cglib.proxy.Enhancer;

/**
 * CGLIB 动态代理类
 *
 * @author Liuyongfei
 * @date 2022/1/23 20:27
 */
public class CglibProxy {

    public static Object getProxy(Class<?> clazz) {
        Enhancer enhancer = new Enhancer();

        // 设置类加载
        enhancer.setClassLoader(clazz.getClassLoader());

        // 设置被代理类
        enhancer.setSuperclass(clazz);

        // 设置方法拦截器
        enhancer.setCallback(new TargetInterceptor());

        // 创建代理类
        return enhancer.create();
    }
}
