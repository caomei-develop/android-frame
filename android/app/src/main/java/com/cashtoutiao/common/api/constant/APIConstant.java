package com.cashtoutiao.common.api.constant;

import com.cashtoutiao.BuildConfig;
import com.squareup.okhttp.MediaType;

public class APIConstant {

	public static String BASE_URL = "";

	static {
		if (BuildConfig.FLAVOR != null && BuildConfig.FLAVOR.equals("dev")) {
			BASE_URL = "";
		}
	}

	public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

	public static final MediaType MEDIA_TYPE_ENCRYPTED_JSON = MediaType.parse("application/encrypted-json");
}
