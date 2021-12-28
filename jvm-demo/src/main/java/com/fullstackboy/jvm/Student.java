package com.fullstackboy.jvm;

/**
 * 类加载器
 *
 * @author Liuyongfei
 * @date 2021/12/26 21:27
 */
public class Student {

    public int age;

    public String name;

    public static void main(String[] args) {
        // 类是模板，对象是具体的实例，实例的引用（内存地址值）在栈里，具体的人（数据）是在堆里。
        // stu1,stu2,stu3 名字存在栈里，具体的值存在堆里
        Student stu1 = new Student();
        Student stu2 = new Student();
        Student stu3 = new Student();

        // 打印这三个实例，他们的hashCode是不一样的
        System.out.println(stu1.hashCode());
        System.out.println(stu2.hashCode());
        System.out.println(stu3.hashCode());

        // 但这三个实例的类模板是一样的（Alt + Enter 快捷键）
        Class<? extends Student> aClass1 = stu1.getClass();
        Class<? extends Student> aClass2 = stu2.getClass();
        Class<? extends Student> aClass3 = stu3.getClass();

        System.out.println(aClass1);
        System.out.println(aClass2);
        System.out.println(aClass3);

        // 获取类加载器
        ClassLoader classLoader = aClass1.getClassLoader();
        System.out.println(classLoader); // AppClassLoader
        System.out.println(classLoader.getParent()); // ExtClassLoader
        System.out.println(classLoader.getParent().getParent()); // null 1.不存在；2.java程序获取不到

    }
}
