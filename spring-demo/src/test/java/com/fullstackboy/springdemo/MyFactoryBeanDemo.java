package com.fullstackboy.springdemo;

import com.fullstackboy.springdemo.factorybean.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试自定义FactoryBean
 *
 * @author Liuyongfei
 * @date 2022/3/14 17:50
 */
public class MyFactoryBeanDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("studentC.xml");

        Student student = (Student) applicationContext.getBean("student");
        // 添加一个 &，则获取到的是 创建Student对象的工厂bean，也就是 StudentFactoryBean
        Object studentFactoryBean = applicationContext.getBean("&student");

        System.out.println(student);
        System.out.println(studentFactoryBean);
    }
}
