package com.fullstackboy.register.client;

/**
 * 心跳响应
 * @author Liuyongfei
 *
 */
public class HeartbeatResponse {
	
	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";

	/**
	 * 心跳响应状态：SUCCESS、FAILURE
	 */
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
