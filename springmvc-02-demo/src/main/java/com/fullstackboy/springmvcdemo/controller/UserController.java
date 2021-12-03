package com.fullstackboy.springmvcdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstackboy.springmvcdemo.pojo.User;
import com.fullstackboy.springmvcdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户控制器
 *
 * @author Liuyongfei
 * @date 2021/12/2 22:18
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 用户行为组件
     */
    @Autowired
    UserService userService;

    /**
     * 用户登录
     * @param model
     * @return
     */
    @RequestMapping("/login")
//    @ResponseBody  // 加上该注解后，controller收到该请求，就不会走视图解析器，而是会直接返回一个字符串。
    public String login(Model model) {
        User user = new User();
        user.setName("bianchengbang");

        userService.login(user);


//        ObjectMapper objectMapper = new ObjectMapper();
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd H:i:s");
//        dateFormat.format(date);

        model.addAttribute("user",user);
        return "login1";
    }

    /**
     * 用户注册
     * @param model
     * @return
     */
    @RequestMapping("/register")
    public String register(Model model) {
        User user = new User();
        user.setName("bianchengbang");

        userService.register(user);

        model.addAttribute("user",user);
        return "register";
    }
}
