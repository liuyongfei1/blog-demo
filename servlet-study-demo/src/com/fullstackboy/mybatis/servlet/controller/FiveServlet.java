package com.fullstackboy.mybatis.servlet.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FiveServlet")
public class FiveServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "http://www.baidu.com";

        // 通过响应对象，将地址赋值给响应头中的location属性
        response.sendRedirect(result);

        // 浏览器在收到响应后，如果发现响应头中有location属性，会自动通过地址栏向location指定的网站发送请求
        // sendRedirect方法远程控制浏览器请求行为【请求地址、请求方式、请求参数】
    }
}
