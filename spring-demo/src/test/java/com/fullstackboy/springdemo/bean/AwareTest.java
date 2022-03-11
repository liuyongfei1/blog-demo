package com.fullstackboy.springdemo.bean;

import com.fullstackboy.springdemo.aop.bean.User2;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * 测试 【Spring 初级容器初始化：忽略指定接口自动装配功能】
 *
 * 目标类 User2，实现了 BeanNameAware 接口，并且 这个 bean 属性对应的setter方法，在感知接口中也存在相同的方法
 * 则，即使在配置文件中给 beanName 属性赋值了，Spring容器也不会给这个属性赋值。
 *
 * 可以看到，即使在 xml配置中给"beanName"属性设置了值，但是最终也不能注入到bean的属性beanName中，反而得到的结果就是"user2"
 * @author Liuyongfei
 * @date 2022/1/14 22:37
 */
public class AwareTest {

    public static void main(String[] args) {

//        ClassPathXmlApplicationContext context =
//                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//        UserB bean = context.getBean(UserB.class);
//
//        System.out.println(bean.getBeanName());

        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        User2 user2 = (User2) beanFactory.getBean("user2");
        System.out.println(user2.getBeanName());
    }
}
