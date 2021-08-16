package com.fullstackboy.register.server;

/**
 * 服务实例信息
 *
 * @author Liuyongfei
 * @date 2021/8/16 19:44
 */
public class ServiceInstance {

    private static final Long NOT_ALIVE_PERIOD = 90 * 1000L;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 服务实例id
     */
    private String serviceInstanceId;

    /**
     * 服务主机名称
     */
    private String hostName;

    /**
     * 服务ip地址
     */
    private String ip;

    /**
     * 服务监听的端口号
     */
    private int port;

    /**
     * 契约信息
     */
    private Lease lease;

    /**
     * 判断服务实例是否存活
     * @return true 存活
     */
    public Boolean isAlive() {
        return new Lease().isAlive();
    }

    /**
     * 契约
     * 维护了一个服务实例跟当前注册中心的一个联系
     * 保罗心跳时间，注册时间等
     */
    private class Lease {
        private Long latestHeartbeatTime = System.currentTimeMillis();

        /**
         * 续约
         */
        public void renew() {
            latestHeartbeatTime = System.currentTimeMillis();
        }

        /**
         * 判断服务实例是否存活
         * @return true: 存活
         */
        public Boolean isAlive() {
            if (System.currentTimeMillis() - latestHeartbeatTime > NOT_ALIVE_PERIOD) {
                return false;
            }
            return true;
        }
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
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

    public Lease getLease() {
        return lease;
    }

    public void setLease() {
        this.lease = new Lease();
    }



    @Override
    public String toString() {
        return "ServiceInstance{" +
                "serviceName='" + serviceName + '\'' +
                ", serviceInstanceId='" + serviceInstanceId + '\'' +
                ", hostName='" + hostName + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", lease=" + lease +
                '}';
    }
}
