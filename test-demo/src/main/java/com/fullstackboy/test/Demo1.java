package com.fullstackboy.test;

/**
 * 基础数据类型扩展及面试题
 *
 * @author Liuyongfei
 * @date 2021/12/28 09:25
 */
public class Demo1 {
    public static void main(String[] args) {
        float d1 = 12313123423423423423f;
        float d2 = d1 + 1;

        // 结果竟然是 true

        // 原因：float，是离散的、有舍入误差、大约、接近但不等于的特点。
        // 所以：最好避免使用浮点数去进行比较
        // 所以：最好避免使用浮点数去进行比较
        // 所以：最好避免使用浮点数去进行比较

        // 那银行业务时，使用BigDecimal，是一个数学工具类。
        System.out.println(d1 == d2);
    }
}
