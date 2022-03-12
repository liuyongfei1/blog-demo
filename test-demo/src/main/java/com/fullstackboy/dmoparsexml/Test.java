package com.fullstackboy.dmoparsexml;

import java.util.List;

/**
 * 测试从XML中读取Student信息
 *
 * @author Liuyongfei
 * @date 2022/3/11 17:18
 */
public class Test {


    public static void main(String[] args) throws Exception {
        // 使用DOM 读取xml 文件信息
        String xmlPath = "/Users/lyf/Workspace/www/blog-demo/test-demo/src/main/resources/student.xml";
        List<Student> students = DomUtil.getStudents(xmlPath);
        System.out.println(students);
    }
}
