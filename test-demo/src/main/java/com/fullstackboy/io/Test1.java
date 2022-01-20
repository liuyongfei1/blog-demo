package com.fullstackboy.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 使用最简单的字节输入流和字节输出流
 * FileInputStream和FileOutStream
 * 来进行测试
 *
 * 总结：FileInputStream和FileOutStream 这两个管道比较细，read()和write()方法也是一个一个的读、一个一个的写。
 * 当遇到大文件时，耗时会比较长。（比如一个64.9M的.mp4文件，从C盘读，然后再写到D盘，需要耗费将近7分钟的时间。）
 * 所以可以考虑用粗管道: BufferedInputStream和BufferedOutStream。
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
