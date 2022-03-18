package com.fullstackboy.myannotations;


import com.fullstackboy.myannotations.bean.Student;

/**
 * 测试自己创建的 ApplicationContext，自己定义的 @MyComponent 注解，完成和 Spring @Component 一样的功能
 *
 * @author Liuyongfei
 * @date 2022/3/18 10:49
 */
public class MyIOCTest {
    public static void main(String[] args) {

        // 指定要扫描的包路径
        String packagePath = "com.fullstackboy.myannotations.bean";

        // 创建文明自己的 ApplicationContext实例
        ApplicationContext  context = new ApplicationContext(packagePath);

        // 获取bean实例
        Student student = (Student) context.getBean("student");

        // 调用实例的 eat 方法
        student.eat();
    }
}
