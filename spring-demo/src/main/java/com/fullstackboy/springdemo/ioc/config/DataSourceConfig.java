package com.fullstackboy.springdemo.ioc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * 数据源配置类
 *
 * Profile 指定组件在哪个环境的情况下才能被注册到容器中。没有指定@Profile时，则表明任何环境下都能注册这个bean。
 *
 * 加了环境标识的bean，只有这个环境被激活的时候才能注册到容器中
 * @author Liuyongfei
 * @date 2022/1/13 13:24
 */
@PropertySource("classpath:/datasource.properties")
@Configuration
public class DataSourceConfig {

    @Value("${druid.url}")
    private String url;

    @Value("${druid.driver}")
    private String driver;

    @Value("${druid.username}")
    private String username;

    @Value("${druid.password}")
    private String password;

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSource2() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSource3() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
