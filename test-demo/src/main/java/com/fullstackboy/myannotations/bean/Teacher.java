package com.fullstackboy.myannotations.bean;

/**
 * 没有使用自定义注解 @MyComponent
 *
 * @author Liuyongfei
 * @date 2022/3/18 07:16
 */
public class Teacher {

    private String name;

    private String age;

    public void eat() {
        System.out.println("这是Teacher类的eat()方法");
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
