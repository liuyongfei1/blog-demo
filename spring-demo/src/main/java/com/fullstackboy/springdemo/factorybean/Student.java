package com.fullstackboy.springdemo.factorybean;

/**
 * TODO Liuyongfei
 *
 * @author Liuyongfei
 * @date 2022/3/14 17:46
 */
public class Student {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
