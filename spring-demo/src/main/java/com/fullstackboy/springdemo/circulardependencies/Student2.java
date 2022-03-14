package com.fullstackboy.springdemo.circulardependencies;

/**
 * 认识一下setter注入循环依赖
 *
 * @author Liuyongfei
 * @date 2022/3/14 09:09
 */
public class Student2 {

    private Student1 student1;

    public Student1 getStudent1() {
        return student1;
    }

    public void setStudent1(Student1 student1) {
        this.student1 = student1;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "student1=" + student1 +
                '}';
    }
}
