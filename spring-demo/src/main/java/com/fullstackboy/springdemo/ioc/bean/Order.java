package com.fullstackboy.springdemo.ioc.bean;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2021/11/1 23:07
 */
public class Order {

    private String name;

    public Order() {
        System.out.println("第一步：通过无参构造函数创建bean");
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("第二步：通过set方法为bean对象设置属性值");
    }

    public void initMethod() {
        System.out.println("第三步：调用初始化方法");
    }

    public void destoryMethod() {
        System.out.println("第五步：调用销毁方法");
    }
}
