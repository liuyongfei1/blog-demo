package com.fullstackboy.springdemo.ioc.bean;

/**
 * 学生实体类
 *
 * @author Liuyongfei
 * @date 2022/1/10 11:02
 */

public class Student {

    private String name;

    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

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
