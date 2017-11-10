package com.cashtoutiao;

import com.cashtoutiao.account.service.UserLocalService;
import com.huadongmedia.common.BaseApplication;
import com.huadongmedia.common.support.AsyncExecuter;
import com.huadongmedia.common.util.AppUtil;

public class AppApplication extends BaseApplication {

	@Override
	public void onCreate() {
		super.onCreate();
		if (AppUtil.isMainProcess(this)) {
			preloadData();
		}
	}

	public void preloadData() {
		AsyncExecuter.submit(new Runnable() {
			@Override
			public void run() {
				// 从文件中读取信息，保证UI线程要使用用户信息的时候，数据已经ready
				UserLocalService.instance().getUserInfo();
			}
		});
	}
}
