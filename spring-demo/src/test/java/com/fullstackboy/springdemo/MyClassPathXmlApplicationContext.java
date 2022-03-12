package com.fullstackboy.springdemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自定义 空实现工厂后处理方法
 *
 * 重写 postProcessBeanFactory 方法
 * 这里将 user2 这个 bean的 Scope 设为 非单例的
 * @author Liuyongfei
 * @date 2022/3/12 15:04
 */
public class MyClassPathXmlApplicationContext extends ClassPathXmlApplicationContext {

    public MyClassPathXmlApplicationContext(String... configLocations) throws BeansException {
        super(configLocations);
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        System.out.println("重写ApplicationContext的postProcessBeanFactory...");

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("user2");
        beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
    }
}
