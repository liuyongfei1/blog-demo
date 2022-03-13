package com.fullstackboy.springdemo;

import com.fullstackboy.springdemo.aop.bean.User2;

/**
 * 测试自定义空实现工厂后处理方法
 *
 * @author Liuyongfei
 * @date 2022/3/12 17:52
 */
public class MyClassPathXmlApplicationContextDemo {
    public static void main(String[] args) {

        // 测试自定义的空实现工厂后处理方法：修改 user2 这个bean为非单例的
        // 因为前后两次创建的实例，指向的是两个地址
        MyClassPathXmlApplicationContext context = new MyClassPathXmlApplicationContext(
                "applicationContext.xml");
        User2 user2 = (User2) context.getBean("user2");
        System.out.println(user2);
        User2 user3 = (User2) context.getBean("user2");
        System.out.println(user3);
        System.out.println(user2.getBeanName());
    }
}
