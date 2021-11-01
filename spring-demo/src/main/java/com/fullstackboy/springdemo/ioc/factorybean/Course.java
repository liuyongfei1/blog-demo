package com.fullstackboy.springdemo.ioc.factorybean;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2021/11/1 22:28
 */
public class Course {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                '}';
    }
}
