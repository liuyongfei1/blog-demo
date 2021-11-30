package com.fullstackboy.mybatis.servlet.controller;

import java.io.IOException;

public class OneServlet extends javax.servlet.http.HttpServlet {

    public OneServlet() {
        System.out.println("OneServlet 被初始化......");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("OneServlet doGet is run......");
    }
}
