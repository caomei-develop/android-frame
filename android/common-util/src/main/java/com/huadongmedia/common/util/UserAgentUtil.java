package com.huadongmedia.common.util;

import com.huadongmedia.common.BaseApplication;

import android.webkit.WebView;

public class UserAgentUtil {

	private static String ua = null;

	public static String getUserAgent() {
		if (ua == null) {
			synchronized (UserAgentUtil.class) {
				if (ua == null) {
					try {
						ua = new WebView(BaseApplication.context).getSettings().getUserAgentString();
					} catch (Throwable e) {
					}
					if (StringUtil.isEmpty(ua)) {
						ua = System.getProperty("http.agent");
					}
					if (ua == null) {
						ua = "";
					} else {
						ua = UnicodeUtil.toUnicode(ua);
					}
				}
			}
		}
		return ua;
	}
}
