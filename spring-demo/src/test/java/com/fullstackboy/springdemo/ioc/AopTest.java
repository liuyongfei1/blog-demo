package com.fullstackboy.springdemo.ioc;

import com.fullstackboy.springdemo.ioc.bean.Calculation;
import com.fullstackboy.springdemo.ioc.config.MainConfigOfAop;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 切面功能测试类
 *
 * @author Liuyongfei
 * @date 2022/1/14 22:37
 */
public class AopTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfAop.class);
        Calculation calculation = context.getBean(Calculation.class);
        calculation.div(1,1);
    }
}
