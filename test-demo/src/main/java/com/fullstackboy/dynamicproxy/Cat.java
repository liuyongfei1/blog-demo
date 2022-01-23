package com.fullstackboy.dynamicproxy;

/**
 * 实现Animal接口
 *
 * @author Liuyongfei
 * @date 2022/1/23 16:56
 */
public class Cat implements Animal{
    @Override
    public void call() {
        System.out.println("喵喵喵~~~");
    }
}
