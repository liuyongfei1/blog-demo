package com.fullstackboy.springdemo;

import com.fullstackboy.springdemo.factorymethod.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 1、静态工厂方法 实例化bean demo
 * 2、实例工厂方法 实例化bean demo
 * @author Liuyongfei
 * @date 2022/3/15 09:12
 */
public class FactoryMethodDemo {

    public static void main(String[] args) {
        // 1、静态工厂方法 实例化bean demo
        // ApplicationContext applicationContext = new ClassPathXmlApplicationContext("studentD.xml");

        // 2、实例工厂方法 实例化bean demo
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("studentE.xml");

        Student student = (Student) applicationContext.getBean("student");
        System.out.println(student.getName());
    }
}
