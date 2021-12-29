package com.fullstackboy.test;

/**
 * 命令行传递参数
 *
 * @author Liuyongfei
 * @date 2021/12/29 13:01
 */
public class Demo7 {

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(i + ":" + args[i]);
        }
    }
}
