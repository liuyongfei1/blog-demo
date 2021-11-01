package com.fullstackboy.springdemo.ioc.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 添加bean的后置处理器
 *
 * @author Liuyongfei
 * @date 2021/11/1 23:26
 */
public class MyBeanPost implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("第三步：在初始化之前执行这个方法");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("第五步：在初始化之后执行这个方法");
        return bean;
    }
}
