package com.fullstackboy.springdemo;

import com.fullstackboy.springdemo.circulardependencies.Student1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 演示 setter注入循环依赖
 *
 * @author Liuyongfei
 * @date 2022/3/14 09:15
 */
public class ApplicationContextDemo {

    public static void main(String[] args) {
         ApplicationContext context = new ClassPathXmlApplicationContext(
                 "student.xml");

        Student1 student1 = (Student1) context.getBean("student1");

        // 会因为循环依赖，导致内存溢出：Exception in thread "main" java.lang.StackOverflowError
        System.out.println(student1);
    }
}
