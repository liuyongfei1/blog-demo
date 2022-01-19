package com.fullstackboy.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 使用最简单的字节输入流和字节输出流
 * FileInputStream和FileOutStream
 * 来进行测试
 *
 * @author Liuyongfei
 * @date 2022/1/20 07:20
 */
public class Test1 {
    public static void main(String[] args) throws Exception {
        // 1.在文件和程序之间铺设管道
        FileInputStream fis = new FileInputStream("/Users/lyf/Downloads/iotest1.txt");

        // 2.创建一个程序通往盘符的管道
        FileOutputStream fos = new FileOutputStream("/Users/lyf/Downloads/iotest2.txt");

        // 3.开闸放水
        int read = 0;
        while ((read = fis.read()) != -1) {
            fos.write(read);
        }

        // 4.关水龙头
        fis.close();
        fos.close();
    }
}
