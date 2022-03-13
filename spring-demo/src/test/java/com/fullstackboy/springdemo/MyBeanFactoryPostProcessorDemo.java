package com.fullstackboy.springdemo;

import com.fullstackboy.springdemo.aop.bean.User2;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试自定义的BeanFactoryPostProcessor
 *
 * 步骤：
 * 1、实现自定义 BeanFactoryPostProcessor
 * 2、在xml配置文件中配置 自定义BeanFactoryPostProcessor，将其作为一个普通的bean注入到Spring容器中
 * 3、写代码测试
 * 4、效果和 MyClassPathXmlApplicationContextDemo 里的是一样的。
 * @author Liuyongfei
 * @date 2022/3/12 17:45
 */
public class MyBeanFactoryPostProcessorDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        User2 user2 = (User2) context.getBean("user2");
        System.out.println(user2);
        User2 user3 = (User2) context.getBean("user2");
        System.out.println(user3);
        System.out.println(user2.getBeanName());
    }
}
