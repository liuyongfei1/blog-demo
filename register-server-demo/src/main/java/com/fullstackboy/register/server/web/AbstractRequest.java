package com.fullstackboy.register.server.web;

/**
 * 请求接口
 *
 * @author Liuyongfei
 * @date 2021/9/26 06:59
 */
public class AbstractRequest {
    /**
     * 服务名称
     */
    private String serviceName;

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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String toString() {
        return "AbstractRequest{" +
                "serviceName='" + serviceName + '\'' +
                ", serverInstanceId='" + serverInstanceId + '\'' +
                '}';
    }
}
