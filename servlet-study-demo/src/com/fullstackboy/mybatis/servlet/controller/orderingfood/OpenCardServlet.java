package com.fullstackboy.mybatis.servlet.controller.orderingfood;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class OpenCardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.调用请求对象获取[请求头]参数信息
        String userName,money;
        userName = request.getParameter("userName");
        money = request.getParameter("money");

        // 2.开卡
        Cookie card1 = new Cookie("userName", URLEncoder.encode(userName, "UTF-8"));
        Cookie card2 = new Cookie("money", money);

        // 3.发卡，将cookie写入到响应头交给浏览器
        response.setContentType("text/html;charset=utf-8");
        response.addCookie(card1);
        response.addCookie(card2);

        // 4.通知Tomcat将【点餐页面】内容写入到响应体交给浏览器
        request.setCharacterEncoding("utf-8");
        request.getRequestDispatcher("/food.html").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
