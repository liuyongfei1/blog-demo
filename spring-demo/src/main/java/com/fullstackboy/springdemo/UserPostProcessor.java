package com.fullstackboy.springdemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2022/3/13 11:40
 */
public class UserPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 修改 User3 的 name 属性值为 777
        User3 user = (User3) bean;
        user.setName("777");
        System.out.println("初始化 before -- 实例化后的bean对象:" + bean + "\t" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化 after -- 实例化后的bean对象:" + bean + "\t" + beanName);
        return bean;
    }

}
