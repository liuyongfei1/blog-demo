package com.fullstackboy.test;

/**
 * 数组三种初始化方式
 *
 * @author Liuyongfei
 * @date 2021/12/30 06:31
 */
public class Demo8 {
    public static void main(String[] args) {
        // 静态初始化
        int[] a = {1,2,3};

        // 动态初始化
        int[] b = new int[5];
        b[0] = 100;
        b[1] = 101;
        b[2] = 102;
        b[3] = 103;
        b[4] = 104;

        // 数组的默认初始化（隐式初始化）
        int[] c = new int[5];
        c[0] = 30;
        // 此时打印，c[1],c[2],c[3],c[4],c[5] 都是0。=》数组一旦分配内存空间，其中的每个元素会被默认初始化（int的默认值是0，String的默认值就是null）
    }
}
