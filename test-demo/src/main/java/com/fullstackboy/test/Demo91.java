package com.fullstackboy.test;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author Liuyongfei
 * @date 2022/4/24 22:09
 */
public class Demo91 {

    public static void main(String[] args) {
        int[] a = {102, 100,80, 60, 93};
        int[] b = sort(a);
        System.out.println(Arrays.toString(b));
    }

    public static int[] sort(int[] arr) {
        int tmp = 0;
        // 外层循环，决定循环几次
        for (int i = 0; i < arr.length; i++) {
            // 内存循环，如果后边的元素比前边的这个元素大，则交换位置
            boolean flag = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j+1] > arr[j]) {
                    tmp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = tmp;

                    flag = true;
                }
            }

            if (!flag) {
                break;
            }
        }

        return arr;
    }
}
