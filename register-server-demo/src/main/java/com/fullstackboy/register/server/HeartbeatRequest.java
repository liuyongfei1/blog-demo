package com.fullstackboy.register.server;

/**
 * 发送心跳的请求
 *
 * @author Liuyongfei
 * @date 2021/8/15 17:56
 */
public class HeartbeatRequest {
    /**
     * 服务名称
     */
    private String serverName;

    /**
     * 服务实例id
     */
    private String serverInstanceId;

    public String getServerInstanceId() {
        return serverInstanceId;
    }

    public void setServerInstanceId(String serverInstanceId) {
        this.serverInstanceId = serverInstanceId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
