package com.fullstackboy.servlet.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * HttpServletRequest对象
 * 2021.11.8
 */
@WebServlet(name = "SixServlet")
public class SixServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> parameterNames = request.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();

            String parameterValue = request.getParameter(parameterName);

            System.out.println("parameterName: " + parameterName + "，parameterValue：" + parameterValue);
        }
    }
}
