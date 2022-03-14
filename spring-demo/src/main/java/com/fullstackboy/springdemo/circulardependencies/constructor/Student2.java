package com.fullstackboy.springdemo.circulardependencies.constructor;

/**
 * 认识一下构造方法循环依赖
 *
 * @author Liuyongfei
 * @date 2022/3/14 09:23
 */
public class Student2 {

    private Student1 student1;

    public Student2(Student1 student1) {
        this.student1 = student1;
    }

    public Student1 getStudent1() {
        return student1;
    }

    public void setStudent1(Student1 student1) {
        this.student1 = student1;
    }
}
