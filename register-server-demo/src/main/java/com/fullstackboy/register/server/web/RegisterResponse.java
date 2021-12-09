package com.fullstackboy.register.server.web;

/**
 * 注册请求的响应
 *
 * @author Liuyongfei
 * @date 2021/8/15 17:55
 */
public class RegisterResponse {

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    /**
     * 注册响应状态
     */
    private String status;

    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
