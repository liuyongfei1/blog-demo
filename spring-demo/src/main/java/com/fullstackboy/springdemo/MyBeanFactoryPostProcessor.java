package com.fullstackboy.springdemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 自定义实现 BeanFactoryPostProcessor
 *
 * @author Liuyongfei
 * @date 2022/3/12 17:36
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    /**
     * Modify the application context's internal bean factory after its standard
     * initialization. All bean definitions will have been loaded, but no beans
     * will have been instantiated yet. This allows for overriding or adding
     * properties even to eager-initializing beans.
     *
     * @param beanFactory the bean factory used by the application context
     * @throws BeansException in case of errors
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("覆盖BeanFactoryPostProcessor的postProcessBeanFactory...");

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("user2");
        beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
    }
}
