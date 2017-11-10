package com.huadongmedia.common.pattern;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

public class Singletons {

	private static Map<Class<?>, Object> instanceMap = new HashMap<>();

	public static <T> T get(Class<T> cls) {
		T result = (T) instanceMap.get(cls);
		if (result == null) {
			synchronized (instanceMap) {
				if (result == null) {
					try {
						result = cls.newInstance();
					} catch (Exception e) {
						Log.e(LOG_TAG, "newInstance exception", e);
					}
				}
			}
		}
		return result;
	}

	private static final String LOG_TAG = "Singletons";
}
