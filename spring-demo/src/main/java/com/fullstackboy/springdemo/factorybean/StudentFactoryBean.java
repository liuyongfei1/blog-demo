package com.fullstackboy.springdemo.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 自定义的FactoryBean
 *
 * getObject 方法包含了创建Student对象的核心逻辑
 *
 * @author Liuyongfei
 * @date 2022/3/14 17:47
 */
public class StudentFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        Student student  = new Student();
        student.setName("tom");
        student.setAge(17);
        return student;
    }

    @Override
    public Class<?> getObjectType() {
        return Student.class;
    }
}
