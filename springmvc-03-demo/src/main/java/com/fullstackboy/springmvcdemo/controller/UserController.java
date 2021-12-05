package com.fullstackboy.springmvcdemo.controller;

import com.fullstackboy.springmvcdemo.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    @RequestMapping("/testExceptionHandle")
    public String testExceptionHandle(@RequestParam("i") Integer i) {
        // 发生ArithmeticException，会被自定义异常类MyExceptionHandler捕捉，自动跳转到error.jsp页面
        System.out.println( 10 / i);
        return "success";
    }
// ********** Spring MVC 异常处理 start ************
//    局部异常处理仅能处理指定 Controller 中的异常
//    @ExceptionHandler({ ArithmeticException.class })
//    public String testArithmeticException(Exception e) {
//        System.out.println("打印错误信息1 ===》 ArithmeticException：" + e);
//
//        // 跳转到指定页面
//        return "error";
//    }
    // ********** Spring MVC 异常处理 end ************

}
