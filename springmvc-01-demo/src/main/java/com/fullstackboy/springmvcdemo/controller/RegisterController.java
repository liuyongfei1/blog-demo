package com.fullstackboy.springmvcdemo.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 注册控制器
 * Controller是控制器接口，接口中只有一个方法handleRequest，用户处理请求和返回ModelAndView
 * @author Liuyongfei
 * @date 2021/12/2 07:25
 */
public class RegisterController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        return new ModelAndView("WEB-INF/jsp/login.jsp");
    }
}
