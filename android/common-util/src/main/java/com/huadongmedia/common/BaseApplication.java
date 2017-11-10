package com.huadongmedia.common;

import com.huadongmedia.common.multidex.MultiDexLoader;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;

public class BaseApplication extends Application {

	public static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		BaseApplication.context = this;
		if (MultiDexLoader.isLoading(this)) {
			return;
		}
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDexLoader.tryInstall(base);
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		clearMemory();
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	public void onTrimMemory(int level) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			super.onTrimMemory(level);
		}
		clearMemory();
	}

	private void clearMemory() {
		onClearMemory();
		System.gc();
	}

	protected void onClearMemory() {
	}
}
