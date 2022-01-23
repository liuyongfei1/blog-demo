package com.fullstackboy.dynamicproxy;

/**
 * 静态代理类
 *
 * @author Liuyongfei
 * @date 2022/1/23 16:47
 */
public class StaticProxyAnimal implements Animal{

    private Animal impl;

    public StaticProxyAnimal(Animal impl) {
        this.impl = impl;
    }

    @Override
    public void call() {
        System.out.println("StaticProxyAnimal静态代理类执行call()方法之前...");
        impl.call();
        System.out.println("StaticProxyAnimal静态代理类执行call()方法之前...");
    }
}
