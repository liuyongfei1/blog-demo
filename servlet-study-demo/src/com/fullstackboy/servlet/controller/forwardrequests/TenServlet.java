package com.fullstackboy.servlet.controller.forwardrequests;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 多个Servlet之间的调用规则：
 * 请求转发解决方案
 * @date 2021-11-09
 */
public class TenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("洗韭菜......");

        // 1.通过当前请求对象生成资源文件申请报告对象
        RequestDispatcher report = request.getRequestDispatcher("/eleven");

        // 2.将报告对象发送给Tomcat
        report.forward(request, response);
    }
}
