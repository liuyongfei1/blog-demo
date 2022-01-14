package com.fullstackboy.springdemo.ioc;

import com.fullstackboy.springdemo.ioc.config.DataSourceConfig;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.sql.DataSource;

/**
 * 数据源分环境切换-测试
 *
 * @author Liuyongfei
 * @date 2022/1/13 14:05
 */
@EnableAspectJAutoProxy
public class DataSourceTest {

    public static void main(String[] args) {
        // *********************************************第一种方式：使用命令行动态参数 *******************************
        // VM options:-Dspring.profiles.active=prod
//        AnnotationConfigApplicationContext  context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
        // *********************************************第一种方式 end*******************************


        // *********************************************第二种方式*******************************
        // 1、创建一个AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext  context = new AnnotationConfigApplicationContext();
        // 2、设置需要激活的环境
        context.getEnvironment().setActiveProfiles("test");
        // 3、注册配置类
        context.register(DataSourceConfig.class);
        // 4、启动刷新容器
        context.refresh();
        // *********************************************第二种方式 end*******************************

        String[] beanNamesForType = context.getBeanNamesForType(DataSource.class);
        for (String bean : beanNamesForType) {
            System.out.println(bean);
        }

    }
}
