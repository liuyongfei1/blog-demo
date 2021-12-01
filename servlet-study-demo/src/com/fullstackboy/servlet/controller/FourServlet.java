package com.fullstackboy.servlet.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FourServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String result = "Java<br/>Mysql<br/>Vue";
        String result = "你好";

        // 默认content-type=text，这样的浏览器不会解析html字符
//        response.setContentType("text/html");
        response.setContentType("text/html;charset=utf-8");

        // 模拟业务逻辑处理结果

        // 1.通过响应对象，向Tomcat索要输出流
        PrintWriter out = response.getWriter();

        // 2.通过输出流将数据以二进制形式发送给【响应体】
        out.print(result);

        // doGet执行完毕，Tomcat将响应体推送给浏览器
    }
}
