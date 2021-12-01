package com.fullstackboy.servlet.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 模拟给全局作用域对象 新增、更新、删除 数据
 * 供ServletContextAttributeListener接口实现类TwoListener监听
 *
 * @date 2021/11/12
 */
public class TwelveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext application = request.getServletContext();

        // 给全局作用域对象新增数据
        application.setAttribute("money", 100);

        //  给全局作用域对象更新数据
        application.setAttribute("money", 200);

        //  给全局作用域对象删除数据
        application.removeAttribute("money");
    }
}
