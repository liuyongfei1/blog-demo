package com.fullstackboy.springdemo.ioc.bean;

import org.springframework.stereotype.Component;

/**
 * 计算类
 *
 * 通过切面类在方法上做增强功能
 *
 * @author Liuyongfei
 * @date 2022/1/14 22:23
 */
@Component
public class Calculation {

    public int div(int x, int y) {
        System.out.println("执行div方法...");
        return x / y;
    }
}
