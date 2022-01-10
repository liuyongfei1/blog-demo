package com.fullstackboy.springdemo.ioc.service;

import com.fullstackboy.springdemo.ioc.bean.Student;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 利用beanFactory的registerSingleton方法
 *
 * 实现一个BeanFactory的后置处理器，在其中就可以获得BeanFactory，这样就可以调用registerSingleton方法
 *
 * @author Liuyongfei
 * @date 2022/1/10 11:08
 */
@Component
public class StudentBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerSingleton("myStudent", new Student("xiaoliu", 30));
    }
}
