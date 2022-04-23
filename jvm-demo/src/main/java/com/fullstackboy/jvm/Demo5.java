package com.fullstackboy.jvm;

/**
 * 动态年龄规则的示例代码
 * 模拟进入老年代
 * 
 * @author Liuyongfei
 * @date 2021/3/2 16:32
 */
public class Demo5 {
    public static void main(String[] args) {
        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = null;
//
//        byte[] array2 = new byte[128 * 1024];
        byte[] array3 = new byte[2 * 1024 * 1024];
//
//        array3 = new byte[2 * 1024 * 1024];
//        array3 = new byte[2 * 1024 * 1024];
//        array3 = new byte[128 * 1024];
//        array3 = null;
//
//        byte[] array4 = new byte[2 * 1024 * 1024];

        // 定义String数组，长度为2，超过2将报数组越界错误
        // Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 2
//        String[] str1 = {"a","b"};
//        String[] str1 = new String[2];
//        str1[0] = "a";
//        str1[1] = "b";
////        str1[2] = "c";
//        System.out.println(Arrays.toString(str1));
//
//        byte[] arr1 = new byte[2];
//        arr1[0] = 126;
//        arr1[1] = 127;
//        System.out.println(Arrays.toString(arr1));

    }
}
