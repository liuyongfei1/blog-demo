package com.fullstackboy.register.server.web;

/**
 * 请求接口
 *
 * @author Liuyongfei
 * @date 2021/9/26 06:59
 */
public abstract class AbstractRequest {

    public static final Integer REGISTER_REQUEST = 1;
    public static final Integer CANCEL_REQUEST = 2;
    public static final Integer HEARTBEAT_REQUEST = 3;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务实例id
     */
    private String serverInstanceId;

    /**
     * 请求类型
     */
    private Integer type;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AbstractRequest{" +
                "serviceName='" + serviceName + '\'' +
                ", serverInstanceId='" + serverInstanceId + '\'' +
                ", type=" + type +
                '}';
    }
}
