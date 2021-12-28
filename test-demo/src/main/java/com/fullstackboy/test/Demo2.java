package com.fullstackboy.test;

/**
 * 类型转换
 * 这里的低、高是容量的意思
 * 且小数的优先级大于整数
 * 低 ---------------------------》高
 * byte,short,char -》 int -》 long -》float -》double
 * 强制转换  (类型)变量名  高-》低
 * 自动转换  低-》高
 * @author Liuyongfei
 * @date 2021/12/28 13:14
 */
public class Demo2 {
    public static void main(String[] args) {
        int i = 128;

        // 从 高 =》低
        // -128 为什么是 -128呢？因为byte类型的范围是-128 ~ 127，你现在给赋了一个128，因此就内存溢出了，内存溢出后会变成什么样子谁也不知道。
        byte b = (byte) i; // 这是强制转换
        System.out.println(b);

        // 从 低=》高
        double c = i;
        System.out.println(c); // 128.0

        /*
        * 注意点：
        * 1.不能对布尔值进行转换
        * 2.不能把对象类型转换为不相干的类型
        * 3.在把大容量转到低容量的时候，需要强制转换
        * 4.转换的时候可能存在内存溢出或者精度问题
        */

        System.out.println((int)23.7); // 23 精度丢失了
        System.out.println((int)-45.89f); // -45  精度丢失了

        char c1 = 'a';
        int d1 = c1 + 1;

        System.out.println(d1);  // 98
        System.out.println((char)d1); // b

        System.out.println("--------------------------");

        // 操作较大的数时，注意溢出问题
        // JDK7新特性，数字之间可以用下划线分割
        int money = 10_0000_0000;
        int years = 20;
        int total = money * years;
        System.out.println(total);  // 计算的时候内存溢出了

        long total2 = money * years; // -1474836480， 默认是int，转换之前就已经存在问题了，所以这样还是不行
        System.out.println(total2); // -1474836480，

        long total3 = money *((long)years); // 20000000000 解决办法：先把一个数据转换为long
        System.out.println(total3);

    }
}
