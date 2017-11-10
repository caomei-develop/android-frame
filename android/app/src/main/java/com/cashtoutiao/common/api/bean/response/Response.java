package com.cashtoutiao.common.api.bean.response;

public class Response {

	private Long userId;

	private int statusCode;

	private String msg;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static final int FAILED = -50;

	public static final int SUCCESS = 200;
}
