package com.fullstackboy.springmvcdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * 用户实体类
 *
 * @author Liuyongfei
 * @date 2021/12/3 18:15
 */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class User {

    public User() {
    }

    @NotNull(message = "用户id不能为空")
    private Integer id;

    @NotNull
    @Length(min = 2, max = 8, message = "用户名不能少于2位大于8位")
    private String name;

    @Email(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]", message = "邮箱格式不正确")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
