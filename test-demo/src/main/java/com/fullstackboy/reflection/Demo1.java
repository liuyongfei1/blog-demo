package com.fullstackboy.reflection;

/**
 * 如何通过反射来创建对象
 *
 * 1、通过类对象调用newInstance()方法  =》 适用于无参构造方法
 * @author Liuyongfei
 * @date 2022/1/8 21:25
 */
public class Demo1 {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Demo1 demo1 = Demo1.class.newInstance();

        Demo1 demo2 = demo1.getClass().newInstance();

        Class demoClass = Class.forName("com.fullstackboy.reflection.Demo1");
        Demo1 demo3 = (Demo1) demoClass.newInstance();

        System.out.println(demo1 instanceof Demo1);
        System.out.println(demo2 instanceof Demo1);
        System.out.println(demo3 instanceof Demo1);

    }
}
