package com.fullstackboy.test;

import java.util.Arrays;

/**
 * 冒泡排序及添加flag标识位优化
 * 将数组元素从大到小排列
 * @author Liuyongfei
 * @date 2021/12/30 06:48
 */
public class Demo9 {

    public static void main(String[] args) {
        int[] a = {123,13423,5,5675,67,767,3,8};
//        int[] a = {767,30,8};
        int[] newArr = sort(a);
        System.out.println(Arrays.toString(newArr));
    }

    public static int[] sort(int[] a) {
        int tmp = 0;
        // 外层循环，判断要比较多少次
        for (int i = 0; i < a.length - 1; i++) {
            System.out.println("外层循环i:" + i);
            // 通过加标识位，来减少没有意义的比较
            boolean flag = false;
            // 内存循环，比较判断两个数，如果后边的比前边的大，则交换位置
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j+1] > a[j]) {
                    tmp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = tmp;
                    flag = true;
                }
            }
            // 表明该数组已经是一个有序的数组了，因此直接退出循环
            if (!flag) {
                break;
            }
        }
        return a;
    }
}
