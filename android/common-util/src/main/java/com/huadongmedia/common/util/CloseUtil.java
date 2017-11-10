package com.huadongmedia.common.util;

import java.net.HttpURLConnection;

public class CloseUtil {

	public static void closeQuietly(HttpURLConnection httpUrlConnection) {
		if (httpUrlConnection != null) {
			try {
				httpUrlConnection.disconnect();
			} catch (Exception e) {
			}
		}
	}

	public static void closeQuietly(AutoCloseable target) {
		if (target != null) {
			try {
				target.close();
			} catch (Exception e) {
			}
		}
	}
}
