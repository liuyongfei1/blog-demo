package com.fullstackboy.springdemo.circulardependencies.constructor;

/**
 * 认识一下构造方法循环依赖
 *
 * @author Liuyongfei
 * @date 2022/3/14 09:23
 */
public class Student1 {

    private Student2 student2;

    public Student1(Student2 student2) {
        this.student2 = student2;
    }

    public Student2 getStudent2() {
        return student2;
    }

    public void setStudent2(Student2 student2) {
        this.student2 = student2;
    }
}
