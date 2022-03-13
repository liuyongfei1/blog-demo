package com.fullstackboy.springdemo;

import com.fullstackboy.springdemo.aop.bean.User2;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试 自定义BeanPostProcessor实现方法，修改bean的属性
 * 重写 postProcessBeforeInitialization 方法，修改 name 属性的值为 777
 * @author Liuyongfei
 * @date 2022/3/13 11:46
 */
public class UserPostProcessorDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("user.xml");
        User3 user = (User3) context.getBean("user3");
        System.out.println(user.getName());
    }
}
