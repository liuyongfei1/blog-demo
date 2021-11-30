package com.fullstackboy.mybatis.register.client;

/**
 * 代表了一个服务实例
 * 里面包含了一个服务实例的所有信息
 * 比如说服务名称、ip地址、hostname、端口号、服务实例id
 * 
 * @author Liuyongfei
 *
 */
public class ServiceInstance {

	/**
	 * 服务名称
	 */
	private String serviceName;
	/**
	 * ip地址
	 */
	private String ip;
	/**
	 * 主机名
	 */
	private String hostname;
	/**
	 * 端口号
	 */
	private int port;
	/**
	 * 服务实例id
	 */
	private String serviceInstanceId;
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getServiceInstanceId() {
		return serviceInstanceId;
	}
	public void setServiceInstanceId(String serviceInstanceId) {
		this.serviceInstanceId = serviceInstanceId;
	}
	
	@Override
	public String toString() {
		return "com.fullstackboy.register.client.ServiceInstance [serviceName=" + serviceName + ", ip=" + ip + ", hostname=" + hostname + ", port="
				+ port + ", serviceInstanceId=" + serviceInstanceId + "]";
	}
	
}
