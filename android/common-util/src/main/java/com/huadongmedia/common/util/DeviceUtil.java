package com.huadongmedia.common.util;

import android.content.ContentResolver;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

public class DeviceUtil {

	public static String getModel() {
		return Build.MODEL;
	}

	public static String getBrand() {
		return Build.BRAND;
	}

	public static String getManufacturer() {
		return Build.MANUFACTURER;
	}

	public static String getOsVersion() {
		return Build.VERSION.RELEASE;
	}

	public static String getAndroidId(Context context) {
		try {
			ContentResolver contentResolver = context.getContentResolver();
			return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID);
		} catch (Exception e) {
		}
		return null;
	}

	public static String getMacAddress(Context context) {
		try {
			WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			return wm.getConnectionInfo().getMacAddress();
		} catch (Exception e) {
		}
		return null;
	}

	public static String getImei(Context context) {
		try {
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			if (tm != null) {
				return tm.getDeviceId();
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static String getImsi(Context context) {
		try {
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			if (tm != null) {
				return tm.getSubscriberId();
			}
		} catch (Exception e) {
		}
		return null;
	}

	public static float getDensity(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.density;
	}

	public static int getScreenWidth(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}

	public static int getScreenHeight(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}
}
