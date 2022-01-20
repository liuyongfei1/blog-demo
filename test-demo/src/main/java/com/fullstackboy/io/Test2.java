package com.fullstackboy.io;

import java.io.*;

/**
 * 使用粗管道 BufferedInputStream和BufferedOutStream
 * 叫 字节缓冲输入流 和字节缓冲输出流
 *
 * 在遇到大文件的情况下，速度还是比较快的
 * @author Liuyongfei
 * @date 2022/1/20 07:50
 */
public class Test2 {

    public static void main(String[] args) throws Exception {
        // 1.在文件和程序之间铺设管道
        FileInputStream fileInputStream = new FileInputStream("/Users/lyf/Downloads/iotest1.txt");
        BufferedInputStream bis = new BufferedInputStream(fileInputStream);

        // 在程序和具体文件之间铺设管道
        OutputStream os = new FileOutputStream("/Users/lyf/Downloads/iotest5.txt");
        BufferedOutputStream bos = new BufferedOutputStream(os);


        // 2.开闸放水
        // 先创造一个车，用来拉水
        // 这个车的大小可以自己定义
        byte[] car = new byte[1024 * 1024];
        int len = 0;
        while ((len = bis.read(car)) != -1) {
            System.out.println(len);
            bos.write(car, 0, len);
        }

        // 3.关水龙头
        bis.close();
        bos.close();
    }
}
