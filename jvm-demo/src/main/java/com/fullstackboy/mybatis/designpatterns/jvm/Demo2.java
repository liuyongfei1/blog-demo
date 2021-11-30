package com.fullstackboy.mybatis.designpatterns.jvm;

/**
 * 通过示例代码，演示一下，老年代的GC是如何触发的
 *
 * @author Liuyongfei
 * @date 2021/3/3 10:51
 */
public class Demo2 {
    public static void main(String[] args) {
        byte[] array1 = new byte[4 * 1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[2 * 1024 * 1024];
        byte[] array3 = new byte[2 * 1024 * 1024];
        byte[] array4 = new byte[2 * 1024 * 1024];
        byte[] array5 = new byte[128 * 1024];

        byte[] array6 = new byte[2 * 1024 * 1024];

    }
}
