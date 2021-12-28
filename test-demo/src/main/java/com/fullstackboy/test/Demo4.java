package com.fullstackboy.test;

/**
 * 位运算符
 *
 * @author Liuyongfei
 * @date 2021/12/29 07:29
 */
public class Demo4 {
    public static void main(String[] args) {
        /*
        * A = 0011 1100
        * B = 0000 1101
        */

        // A&B（与，两个都为1，才为1）=》0000 1100
        // A|B（或，如果有1个为1，结果就为1）=》0011 1101
        // A^B（异或，如果这两个相同，则为0，否则则为1）=》0011 0001
        // ~B （取反）=》1111 0010

        // 这就是位运算，与二进制相关的。
        // ****************引出一道面试题，2*8 = 16，怎么计算最快？******************
        // 计算机正常计算这个的话，在底层要做许多事情，因为底下都是电路。
        // 2*2*2*2 = 16，使用位运算，计算效率会非常快，换一下灯亮，改变一下高低电路就行了
        // 这时就可以使用 <<,>> 左移、右移 运算符
        System.out.println(2<<3); // 2 左移3位，16

        /*
        * 分析一波，为什么 2<<3，左移3位，就等于16了呢？
        * 0000 0001   1
        * 0000 0010   2
        * 0000 0100   4
        * 0000 1000   8
        * 0001 0000   16
        *
        * 可以发现一个规律，往左移动一位，值就会变大。2 左移3位，就是16
        * 左移一位，相当于 *2
        * 右移一位，相当于 /2
        */

        }
}
