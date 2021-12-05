package com.fullstackboy.springmvcdemo.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 自定义的异常处理器
 * 包括处理器异常、数据绑定异常以及控制器执行时发生的异常。
 * @author Liuyongfei
 * @date 2021/12/5 10:56
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        HashMap<String, Object> model = new HashMap<String, Object>();
        // 根据不同错误转向不同页面（统一处理），即异常与View的对应关系
        if (e instanceof ArithmeticException) {
            return new ModelAndView("error", model);
        }
        return new ModelAndView("error-2", model);
    }
}
