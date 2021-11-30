package com.fullstackboy.mybatis.servlet.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 问题：
 *    POST请求时，如果请求参数的值包含中文，则会收到乱码
 *
 * 原因：
 *    - 浏览器以get方式发送请求，请求参数保存在【请求头】中，在Http请求协议包到达Http服务器之后，第一件事就是进行解码。请求头二进制内容由Tomcat负责解码。Tomcat9.0默认就是使用【utf-8】字符集进行解码，可以解释一切国家文字；
 *    - 浏览器以get方式发送请求，请求参数保存在【请求体】中，在Http请求协议包到达Http服务器之后，第一件事就是进行解码。请求体二进制内容由当前请求对象（request）负责解码，request默认使用【ISO-8859
 *  -1】字符集，一个东欧语系字符集。此时如果请求体中包含中文，将无法解码只能得到乱码。
 * 解决方案：
 *     在POST请求方式下，在读取请求体内容之前，应该通知请求对象使用utf-8字符集对请求内容进行一次重新解码
 *
 * @date 2021-11-08
 */
@WebServlet(name = "SeventhServlet")
public class SeventhServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 通知请求对象，使用utf-8字符集对请求体二进制内容进行一次重新解码
        request.setCharacterEncoding("utf-8");
        // 通过请求对象，得到【请求体】参数信息
        String value = request.getParameter("username");
        System.out.println(value);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 通过请求对象，得到【请求参数】参数信息
        String value = request.getParameter("username");
        System.out.println(value);
    }
}
