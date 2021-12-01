package com.fullstackboy.servlet.controller.redirect;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 多个Servlet之间的调用规则：
 * 重定向解决方案
 * @date 2021-11-09
 */
public class EightServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("洗韭菜......");
        response.sendRedirect("/myWeb/nine");
    }
}
