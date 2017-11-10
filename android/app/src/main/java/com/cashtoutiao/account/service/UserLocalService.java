package com.cashtoutiao.account.service;

import java.io.File;

import android.util.Log;

import com.cashtoutiao.account.bean.UserInfo;
import com.huadongmedia.common.BaseApplication;
import com.huadongmedia.common.support.AsyncExecuter;
import com.huadongmedia.common.util.FileUtil;
import com.huadongmedia.common.util.JSONUtil;

public class UserLocalService {

	private static UserLocalService userLocalService = new UserLocalService();

	private UserInfo userInfo;

	public static UserLocalService instance() {
		return userLocalService;
	}

	private UserLocalService() {
	}

	public UserInfo getUserInfo() {
		if (userInfo == null) {
			load();
		}
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		if (userInfo == null) {
			userInfo = new UserInfo();
		}
		this.userInfo = userInfo;
		flush();
	}

	public Long getUserId() {
		return getUserInfo().getId();
	}

	public String getLoginId() {
		return getUserInfo().getLoginId();
	}

	public String getPhone() {
		return getUserInfo().getPhone();
	}

	private void flush() {
		AsyncExecuter.submit(new Runnable() {
			@Override
			public void run() {
				try {
					FileUtil.writeStringToFile(BaseApplication.context.getFileStreamPath(USER_INFO_FILE), JSONUtil.toJSONString(userInfo));
				} catch (Exception e) {
					Log.e(LOG_TAG, "flush user info exception", e);
				}
			}
		});
	}

	private synchronized void load() {
		if (userInfo != null) {
			return;
		}
		try {
			File file = BaseApplication.context.getFileStreamPath(USER_INFO_FILE);
			if (file.exists()) {
				UserInfo userInfo = JSONUtil.parseObject(FileUtil.readFileToString(file), UserInfo.class);
			}
		} catch (Exception e) {
			Log.e(LOG_TAG, "read user info exception", e);
		}
		if (userInfo == null) {
			userInfo = new UserInfo();
		}
	}

	private static final String LOG_TAG = "UserLocalService";

	public static final String USER_INFO_FILE = ".user.info";
}
