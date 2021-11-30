package com.fullstackboy.springdemo.proxy;

/**
 * 动态代理测试
 *
 * @author Liuyongfei
 * @date 2021/7/31 10:40
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        IPerson person = (IPerson) DynamicProxyFactory.getProxy(new IPersonImpl());
        person.sleep();
        person.eating();
    }
}
