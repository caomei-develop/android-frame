package com.huadongmedia.common.multidex;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.huadongmedia.common.multidex.activity.MultiDexLoadActivity;
import com.huadongmedia.common.util.AppUtil;
import com.huadongmedia.common.util.FileUtil;
import com.huadongmedia.common.util.StringUtil;

import java.io.IOException;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

public class MultiDexLoader {

	public static boolean isLoading(Context context) {
		if (StringUtil.contains(AppUtil.getCurrentProcessName(context), ":mini")) {
			return true;
		}
		return false;
	}

	public static void tryInstall(Context context) {
		if (!isLoading(context)) {
			if (!isLoaded(context)) {
				waitInstall(context);
			}
			MultiDex.install(context);
		}
	}

	public static boolean isLoaded(Context context) {
		String flag = get2thDexSHA1(context);
		if (StringUtil.isEmpty(flag)) {
			return true;
		}
		String digest = null;
		try {
			digest = FileUtil.readFileToString(context.getFileStreamPath(DIGEST_FILE));
		} catch (IOException e) {
		}
		return StringUtil.equals(flag, digest);
	}

	public static void waitInstall(Context context) {
		Intent intent = new Intent();
		ComponentName componentName = new ComponentName(context.getPackageName(), MultiDexLoadActivity.class.getName());
		intent.setComponent(componentName);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
		long startWait = System.currentTimeMillis();
		long waitTime = 10 * 1000;
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
			waitTime = 20 * 1000;// 实测发现某些场景下有些2.3版本有可能10s都不能完成optdex
		}
		while (!isLoaded(context)) {
			try {
				long nowWait = System.currentTimeMillis() - startWait;
				Log.d(LOG_TAG, "waitforInstall ms :" + nowWait);
				if (nowWait >= waitTime) {
					return;
				}
				Thread.sleep(200);
			} catch (InterruptedException e) {
				Log.e(LOG_TAG, "waitforInstall exception", e);
			}
		}
	}

	public static String get2thDexSHA1(Context context) {
		ApplicationInfo ai = context.getApplicationInfo();
		String source = ai.sourceDir;
		try {
			JarFile jar = new JarFile(source);
			java.util.jar.Manifest mf = jar.getManifest();
			Map<String, Attributes> map = mf.getEntries();
			Attributes a = map.get("classes2.dex");
			return a.getValue("SHA1-Digest");
		} catch (Exception e) {
			Log.e(LOG_TAG, "get2thDexSHA1 exception", e);
		}
		return null;
	}

	public static void actualInstall(Context context) {
		try {
			MultiDex.install(context);
			FileUtil.writeStringToFile(context.getFileStreamPath(DIGEST_FILE), get2thDexSHA1(context));
		} catch (IOException e) {
			Log.e(LOG_TAG, "actualInstall exception", e);
		}
	}

	private static final String LOG_TAG = "MultiDexLoader";

	public static final String DIGEST_FILE = ".digest.info";
}
