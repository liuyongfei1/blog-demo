package com.fullstackboy.springdemo.ioc.bean;

/**
 * Person类
 *
 * @author Liuyongfei
 * @date 2022/1/16 21:47
 */
public class Person {

    private int id;

    private String name;

    private String beanName;

    public Person() {
        System.out.println("Person 被实例化......");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("设置：" + name);
        this.name = name;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    /**
     * 自定义的初始化方法
     */
    public void start() {
        System.out.println("Person中自定义的初始化方法......");
    }

}
