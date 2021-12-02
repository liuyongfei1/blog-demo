package com.fullstackboy.springmvcdemo.controller;

import com.fullstackboy.springmvcdemo.pojo.User;
import com.fullstackboy.springmvcdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String login(Model model) {
        User user = new User();
        user.setName("bianchengbang");

        userService.login(user);

        model.addAttribute("user",user);
        return "login";
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
