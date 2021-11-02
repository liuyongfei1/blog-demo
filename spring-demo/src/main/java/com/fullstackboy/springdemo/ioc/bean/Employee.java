package com.fullstackboy.springdemo.ioc.bean;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2021/11/2 08:25
 */
public class Employee {
    private String name;

    private Department dept;

    public Employee() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }
}
