package com.huadongmedia.common.util;

import java.util.List;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

public class AppUtil {

	public static boolean isMainProcess(Context context) {
		ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
		List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
		String mainProcessName = context.getPackageName();
		int myPid = Process.myPid();
		for (ActivityManager.RunningAppProcessInfo info : processInfos) {
			if (info.pid == myPid && mainProcessName.equals(info.processName)) {
				return true;
			}
		}
		return false;
	}

	public static String getCurrentProcessName(Context context) {
		int pid = Process.myPid();
		ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
			if (appProcess.pid == pid) {
				return appProcess.processName;
			}
		}
		return null;
	}

	public static String getVersionName(Context context) {
		try {
			String versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
			return versionName;
		} catch (Exception e) {
		}
		return "Unknown";
	}

	public static int getVersionCode(Context context) {
		int versionCode = -1;
		try {
			versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (Exception e) {
		}
		return versionCode;
	}
}
