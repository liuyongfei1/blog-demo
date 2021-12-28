package com.fullstackboy.test;

/**
 * 一元运算符
 * ++,--
 * @author Liuyongfei
 * @date 2021/12/29 07:15
 */
public class Demo3 {
    public static void main(String[] args) {
        int a = 3;

        int b = a++; // 执行完这段代码后，先给b赋值，再自增。相当于，这段代码后边加一行：
        // a = a + 1;

        // a = a + 1;
        int c = ++a; // 执行完这段代码前，先自增，再给b赋值。相当于，这段代码前边加一行

        System.out.println(a); // 5
        System.out.println(b); // 3
        System.out.println(c); // 5
    }
}
