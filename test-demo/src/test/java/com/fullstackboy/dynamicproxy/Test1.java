package com.fullstackboy.dynamicproxy;


import org.junit.Test;

/**
 * 动态代理相关测试
 * 1、静态代理
 * 2、JDK动态代理
 * 3、CGLIB动态代理
 *
 * @author Liuyongfei
 * @date 2022/1/23 16:54
 */
public class Test1 {

    /**
     * 1、静态代理
     */
    @Test
    public void testStaticProxy() {
        Cat cat = new Cat();

        StaticProxyAnimal proxy = new StaticProxyAnimal(cat);
        proxy.call();
    }
}
