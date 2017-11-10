package com.cashtoutiao.common.api.constant;

import com.cashtoutiao.BuildConfig;
import com.squareup.okhttp.MediaType;

public class APIConstant {

	public static String BASE_URL = "http://api.cashtoutiao.com/frontend";

	static {
		if (BuildConfig.FLAVOR != null && BuildConfig.FLAVOR.equals("dev")) {
			BASE_URL = "http://192.168.0.247:9780/frontend";
		}
	}

	public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

	public static final MediaType MEDIA_TYPE_ENCRYPTED_JSON = MediaType.parse("application/encrypted-json");
}
