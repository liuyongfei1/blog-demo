package com.fullstackboy.test;

/**
 * 打印一个5行的三角形
 *
 * @author Liuyongfei
 * @date 2021/12/29 09:24
 */
public class Demo6 {

    public static void main(String[] args) {
        for (int i = 1; i <= 5 ; i++) {
            for (int j = 5; j >= i ; j--) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i ; j++) {
                System.out.print("*");
            }
            for (int j = 1; j < i ; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
