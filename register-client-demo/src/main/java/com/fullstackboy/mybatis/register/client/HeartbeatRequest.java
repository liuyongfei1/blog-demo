package com.fullstackboy.mybatis.register.client;

/**
 * 心跳请求
 * @author Liuyongfei
 *
 */
public class HeartbeatRequest {

	/**
	 * 服务名称
	 */
	private String serviceName;
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
	public String getServiceInstanceId() {
		return serviceInstanceId;
	}
	public void setServiceInstanceId(String serviceInstanceId) {
		this.serviceInstanceId = serviceInstanceId;
	}
	
	@Override
	public String toString() {
		return "com.fullstackboy.register.client.HeartbeatRequest [serviceName=" + serviceName + ", serviceInstanceId=" + serviceInstanceId + "]";
	}
	
}
