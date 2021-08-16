package com.fullstackboy.register.server;

/**
 * 注册请求
 *
 * @author Liuyongfei
 * @date 2021/8/15 17:55
 */
public class RegisterRequest {

    /**
     * 服务实例id
     */
    private String serverInstanceId;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务所在机器的主机名
     */
    private String hostName;

    /**
     * 服务所在机器的ip地址
     */
    private String ip;

    /**
     * 服务监听着的那个端口号
     */
    private int port;


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

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
