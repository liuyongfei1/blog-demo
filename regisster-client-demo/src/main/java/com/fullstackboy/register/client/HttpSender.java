package com.fullstackboy.register.client;

/**
 * 发送各种请求
 *
 * @author Liuyongfei
 * @date 2021/8/15 17:54
 */
public class HttpSender {

    /**
     * 发送注册请求
     * @param request 请求参数
     * @return 响应
     */
    public RegisterResponse register(RegisterRequest request) {
        System.out.println("服务实例【" + request + "】开始发送注册请求");
        RegisterResponse response = new RegisterResponse();
        response.setStatus(RegisterResponse.SUCCESS);
        return response;
    }

    /**
     * 发送心跳请求
     * @param request 请求参数
     * @return 响应
     */
    public HeartbeatResponse heartbeat(HeartbeatRequest request) {
        System.out.println("服务实例【" + request.getServerInstanceId() + "】开始发送心跳请求");
        HeartbeatResponse response = new HeartbeatResponse();
        response.setStatus(HeartbeatResponse.SUCCESS);
        return response;
    }
}
