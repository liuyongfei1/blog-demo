package com.fullstackboy.test;

import com.fullstackboy.myannotations.MyComponent;

/**
 * 基础数据类型扩展及面试题
 *
 * @author Liuyongfei
 * @date 2021/12/28 09:25
 */
@MyComponent
public class Demo1 {
    public static void main(String[] args) {
        // 整数型拓展

        int i =  10;
        // 八进制以0开头
        int i2 = 010;

        // 十六进制以0x开头  0~9 + A-F
        int i3 = 0x10;
        System.out.println(i);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println("------------");

        // 浮点数拓展
        float d1 = 12313123423423423423f;
        float d2 = d1 + 1;

        // 结果竟然是 true

        // 原因：float，是离散的、有舍入误差、大约、接近但不等于的特点。
        // 所以：最好避免使用浮点数去进行比较
        // 所以：最好避免使用浮点数去进行比较
        // 所以：最好避免使用浮点数去进行比较

        // 那银行业务时，使用BigDecimal，是一个数学工具类。
        System.out.println(d1 == d2);

        System.out.println("------------");
        // 字符拓展
        char c1 = 'a';
        char c2 = '中';
        System.out.println(c1);

        // 强制转换，所有的字符本质还是数字  char会涉及到一个编码问题，Unicode可以处理各种编码，占2个字节  0~ 65536
        // 编码 Unicode表: （97 = a,  65 = A）

        // 经常会看到使用Unicode编码使用转义来表示  U0000 UFFFF
         char c3 = '\u0061'; // 这是正常的unicode编码

        System.out.println((int)c1);  // 强制转换   97
        System.out.println(c2);
        System.out.println((int)c2); // 强制转换   20013
        System.out.println(c3);  // a

        // 转义字符
        // \t 制表符
        // \n 换行
        // ......
        System.out.println("Hello\nWorld"); // 会输出换行的 Hell World
    }
}
