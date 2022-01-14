package com.fullstackboy.springdemo.aop.config;

import com.fullstackboy.springdemo.aop.bean.Calculation;
import com.fullstackboy.springdemo.aop.bean.LogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2022/1/14 22:22
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAop {

    /**
     * 将业务逻辑类加入容器中
     * @return Calculation
     */
    @Bean
    public Calculation calculation() {
        return new Calculation();
    }

    /**
     * 将切面类加入容器中
     * @return LogAspect
     */
    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }
}
