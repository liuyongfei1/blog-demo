package com.fullstackboy.dynamicproxy;

/**
 * 实现Animal接口
 *
 * @author Liuyongfei
 * @date 2022/1/23 16:56
 */
public class Cat implements Animal{
    @Override
    public String call() {
        System.out.println("喵喵喵~~~");
        return "喵喵喵~~~";
    }

    @Override
    public String sleep() {
        return "睡觉";
    }
}
