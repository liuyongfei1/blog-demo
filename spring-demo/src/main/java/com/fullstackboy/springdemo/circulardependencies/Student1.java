package com.fullstackboy.springdemo.circulardependencies;

/**
 * 认识一下setter注入循环依赖
 *
 * @author Liuyongfei
 * @date 2022/3/14 09:09
 */
public class Student1 {

    private Student2 student2;

    public Student2 getStudent2() {
        return student2;
    }

    public void setStudent2(Student2 student2) {
        this.student2 = student2;
    }

    @Override
    public String toString() {
        return "Student1{" +
                "student2=" + student2 +
                '}';
    }
}
