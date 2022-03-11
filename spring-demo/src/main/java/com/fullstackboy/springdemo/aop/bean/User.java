package com.fullstackboy.springdemo.aop.bean;


import org.springframework.beans.factory.BeanNameAware;

/**
 * 演示 Spring 感知接口有什么作用
 *
 * 实现了 感知接口的这些 bean，当 spring在实例化这些 bean 的时候，就会调用感知接口中的方法注入相应的数据
 *
 * @author Liuyongfei
 * @date 2022/3/9 23:34
 */
public class User implements BeanNameAware {

    private String myName;

    @Override
    public void setBeanName(String name) {
        System.out.println("User 这个bean在spring容器中的 bean id是：" + name);

        myName = name;
    }

    public String getMyName() {
        return "【" + myName + "】";
    }
}
