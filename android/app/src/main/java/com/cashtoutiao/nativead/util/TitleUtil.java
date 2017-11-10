package com.cashtoutiao.nativead.util;

import com.huadongmedia.common.util.StringUtil;

public class TitleUtil {

	public static String chooseLonger(String title, String subtitle) {
		if (!StringUtil.isEmpty(title)) {
			if (title.length() >= 15) {
				return title;
			}
			if (!StringUtil.isEmpty(subtitle) && subtitle.length() > title.length()) {
				return subtitle;
			}
			return title;
		} else if (!StringUtil.isEmpty(subtitle)) {
			return subtitle;
		} else {
			return "快来点击，发现未知世界，探索更多精彩";
		}
	}
}
