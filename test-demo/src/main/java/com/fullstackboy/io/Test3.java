package com.fullstackboy.io;

import java.io.FileReader;
import java.io.FileWriter;

/**
 * 使用字符流：
 * 字符输入流：FileReader和BufferedReader
 * 字符输出流：FileWriter和BufferedWriter
 * utf-8编码下，一个汉字占3个字节
 * gb2312编码下，一个汉字占2个字节
 *
 * 1、字符流读取纯文本文件比较方便（像excel、ppt、word 这些不属于纯文本文件），已经帮我们处理了乱码问题。
 * 2、字符流不能读取图片、音频、视频文件。
 *
 * 使用字符流读取纯文本文件时包含汉字，不会出现乱码。
 * @author Liuyongfei
 * @date 2022/1/20 09:58
 */
public class Test3 {
    public static void main(String[] args) throws Exception {
        // 1、水厂数据：iotest-0120.txt
        // 2、铺设管道:盘符-》程序
        FileReader fr = new FileReader("/Users/lyf/Downloads/iotest-0120.txt");

        FileWriter fw = new FileWriter("/Users/lyf/Downloads/iotest-0120-2.txt");

        // 3、开闸放水
        char[] car = new char[1024 * 1024];
        int len = 0;
        while ((len = fr.read(car))  != -1) {
            String content  = new String(car, 0, len);
            System.out.println(content);
            fw.write(car);
        }

        // 4、关水龙头
        fr.close();
    }
}
