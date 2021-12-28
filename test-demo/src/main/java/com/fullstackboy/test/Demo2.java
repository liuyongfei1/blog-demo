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
    }
}
