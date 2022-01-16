package com.fullstackboy.springdemo.ioc.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 自定义的BeanPostProcessor实现类
 *
 * BeanPostProcessor接口的作用是：
 * 我们可以通过该接口中的方法，在bean实例化、配置以及其它初始化方法的前后添加一些我们自己的逻辑
 *
 * @author Liuyongfei
 * @date 2022/1/16 21:56
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * 实例化、依赖注入完毕，在调用显示的初始化之前完成一些定制的初始化任务
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化 before -- 实例化的bean对象：" + bean + "\t" + beanName);
        return bean;
    }

    /**
     * 实例化、依赖注入完毕，在调用显示的初始化完毕时执行
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("初始化 after -- 实例化的bean对象：" + bean + "\t" + beanName);
        return bean;
    }
}
