package com.cashtoutiao.common.api.bean.request;

import com.alibaba.fastjson.annotation.JSONField;

public class Request {

	private Long userId; // 链接上和这里都需要传一份，且后台会自动验证一致性

	private short platform = 0;

	@JSONField(name = "appVersion")
	private int appVersionCode;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public short getPlatform() {
		return platform;
	}

	public void setPlatform(short platform) {
		this.platform = platform;
	}

	public int getAppVersionCode() {
		return appVersionCode;
	}

	public void setAppVersionCode(int appVersionCode) {
		this.appVersionCode = appVersionCode;
	}
}
