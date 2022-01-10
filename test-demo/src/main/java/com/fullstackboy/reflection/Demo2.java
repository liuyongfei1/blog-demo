package com.fullstackboy.reflection;

/**
 * 如何通过反射来创建对象
 *
 * 2、通过getConstructor或getDeclaredConstructor方法获得构造器（Constructor）对象；
 *    并调用其newInstance方法创建对象；
 *
 * 适用于无参和有参构造方法
 * @author Liuyongfei
 * @date 2022/1/8 21:50
 */
public class Demo2 {
    private String str;
    private int num;

    public Demo2() {
    }

    public Demo2(String str, int num) {
        this.str = str;
        this.num = num;
    }

    public Demo2(String str) {
        this.str = str;
    }

    public static void main(String[] args) throws Exception {
        Class[] classes = new Class[] {String.class, int.class};

        Demo2 demo = Demo2.class.getConstructor(classes).newInstance("hello", 30);
        System.out.println(demo.str);

        Demo2 demo2 = Demo2.class.getDeclaredConstructor(String.class).newInstance("hello2");
        System.out.println(demo2.str);

        Demo2 demo3 = (Demo2) Class.forName("com.fullstackboy.reflection.Demo2").getConstructor().newInstance();
        System.out.println(demo3 instanceof Demo2);
    }
}
