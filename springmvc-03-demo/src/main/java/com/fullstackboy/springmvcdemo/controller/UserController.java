package com.fullstackboy.springmvcdemo.controller;

import com.fullstackboy.springmvcdemo.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户控制器
 *
 * @author Liuyongfei
 * @date 2021/12/3 18:13
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public String getLogin(Model model) {
        User user = new User();
        user.setName("编程帮");
        model.addAttribute("user", user);

        return "login";
    }

    @RequestMapping("/register")
    public String getRegisterUser() {
        return "register";
    }

    @RequestMapping("/validate")
    public String validate(@Valid User user, BindingResult bindingResult) {
        // 如果有异常信息
        if (bindingResult.hasErrors()) {
            // 获取异常信息
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            // 遍历，打印异常信息
            for (ObjectError error : allErrors) {
                System.out.println(error.getDefaultMessage());
            }
        }
        return "redirect:/index.jsp";
    }

    @RequestMapping("/addUser")
    public String add() {

        return "addUser";
    }
}
