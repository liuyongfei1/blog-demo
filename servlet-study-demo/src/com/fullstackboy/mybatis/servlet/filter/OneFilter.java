package com.fullstackboy.mybatis.servlet.filter;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet过滤器实现类：
 * 1.拦截Http服务器，帮助Http服务器检测当前请求的合法性;
 * 2.拦截Http服务器，对当前请求做增强操作
 *
 * @author Liuyongfei
 * @date 2021/11/13 16:05
 */
public class OneFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String age = servletRequest.getParameter("age");

        if (Integer.valueOf(age) < 70) {
            // 将拦截请求对象和响应对象交还给Tomcat，由Tomcat继续调用资源文件
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 过滤器替代Http服务器拒绝本次请求
            servletResponse.setContentType("text/html;charset=utf-8");

            PrintWriter out = servletResponse.getWriter();
            out.print("<center><font style='color:red; font-size: 40px'>大爷，珍爱生命呢！</center>");
        }


    }

    @Override
    public void destroy() {

    }
}
