package com.fullstackboy.springdemo.ioc.bean;

/**
 * 教师实体类
 *
 * @author Liuyongfei
 * @date 2022/1/11 07:50
 */

public class Teacher {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
