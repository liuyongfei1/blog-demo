package com.fullstackboy.servlet.controller.orderingfood;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FoodServlet")
public class FoodServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String food = null;
        String userName = null;
        Integer money = 0, pay = 0, accountBalance = 0;
        Cookie cookieArray[] = null;

        Cookie newCard = null;

        // 1.读取点餐类型
        food = request.getParameter("food");

        // 2.读取请求中的cookie
        cookieArray = request.getCookies();


        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();

        // 3.刷卡消费
        for (Cookie card : cookieArray) {
            String key = card.getName();
            String value =  card.getValue();

            if ("userName".equals(key)) {
                userName = value;
            } else if ("money".equals(key)) {
                money = Integer.valueOf(value);

                if ("饺子".equals(food)) {
                    if (30 > money) {
                        out.print("用户：" + userName + "，余额不足，请充值");
                    } else {
                        newCard = new Cookie("money", (money - 30) + "");
                        pay = 30;
                        accountBalance = money - 30;
                    }
                } else if ("面条".equals(food)) {
                    if (20 > money) {
                        out.print("用户：" + userName + "，余额不足，请充值");
                    } else {
                        newCard = new Cookie("money", (money - 20) + "");
                        pay = 20;
                        accountBalance = money - 20;
                    }
                } else if ("盖饭".equals(food)) {
                    if (15 > money) {
                        out.print("用户：" + userName + "，余额不足，请充值");
                    }  else {
                        newCard = new Cookie("money", (money - 15) + "");
                        pay = 15;
                        accountBalance = money - 15;
                    }
                }

            }
        }

        // 4. 将用户会员卡返回给用户
        response.addCookie(newCard);

        // 5. 将消费记录写入到响应
        out.print("用户:" + userName + "，本次消费：" + pay +  "，余额： " +  accountBalance);

    }
}
