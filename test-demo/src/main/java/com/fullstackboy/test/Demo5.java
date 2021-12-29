package com.fullstackboy.test;

/**
 * 打印九九乘法表
 *
 * @author Liuyongfei
 * @date 2021/12/29 08:14
 */
public class Demo5 {
    public static void main(String[] args) {
        for (int j = 1; j <= 9 ; j++) {
            for (int i = 1; i <= j ; i++) {
                System.out.print(j + "*" + i + "=" + (j * i) + "\t");  // tab换行
            }
            System.out.println();  // 换行
        }
    }
}
