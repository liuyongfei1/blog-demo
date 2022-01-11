package com.fullstackboy.springdemo.ioc.service;

import com.fullstackboy.springdemo.ioc.bean.Student;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * 2、通过使用FactoryBean
 *
 * @author Liuyongfei
 * @date 2022/1/11 07:03
 */
//@Component
public class StudentFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new Student("lisi",50);
    }

    @Override
    public Class<?> getObjectType() {
        return Student.class;
    }
}
