package com.fullstackboy.springdemo.factorymethod;

/**
 * 工厂类
 *
 * 这里的getStudent方法逻辑很简单。
 * 实际，我们可以根据自己的需求，来自定义实例化bean的逻辑
 * @author Liuyongfei
 * @date 2022/3/15 09:08
 */
public class StudentFactory {

    public static Student getStudentObject() {
        return new Student();
    }
}
