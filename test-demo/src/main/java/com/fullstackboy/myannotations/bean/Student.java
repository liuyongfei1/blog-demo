package com.fullstackboy.myannotations.bean;

import com.fullstackboy.myannotations.MyComponent;

/**
 * 使用自定义注解 @MyComponent
 *
 * @author Liuyongfei
 * @date 2022/3/18 07:15
 */
@MyComponent
public class Student {

    private String name;
    private String age;

    public void eat() {
        System.out.println("这是Student类的eat()方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
