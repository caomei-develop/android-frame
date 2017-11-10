package com.huadongmedia.common.preference;

import java.util.Map;

import com.huadongmedia.common.BaseApplication;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

	private static PreferenceManager preferenceManager;

	private SharedPreferences sp;

	public static PreferenceManager instance() {
		if (preferenceManager == null) {
			synchronized (PreferenceManager.class) {
				if (preferenceManager == null) {
					preferenceManager = new PreferenceManager(BaseApplication.context, "common.preferences");
				}
			}
		}
		return preferenceManager;
	}

	protected PreferenceManager(Context context, String name) {
		sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
	}

	public void putString(String key, String value) {
		SharedPreferences.Editor editor = sp.edit();
		editor.putString(key, value);
		editor.apply();
	}

	public void putInt(String key, int value) {
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt(key, value);
		editor.apply();
	}

	public void putFloat(String key, float value) {
		SharedPreferences.Editor editor = sp.edit();
		editor.putFloat(key, value);
		editor.apply();
	}

	public void putLong(String key, long value) {
		SharedPreferences.Editor editor = sp.edit();
		editor.putLong(key, value);
		editor.apply();
	}

	public void putBoolean(String key, boolean value) {
		SharedPreferences.Editor editor = sp.edit();
		editor.putBoolean(key, value);
		editor.apply();
	}

	public void putMap(Map<String, Object> keyValues) throws IllegalArgumentException {
		SharedPreferences.Editor editor = sp.edit();
		for (Map.Entry<String, Object> entry : keyValues.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof Integer) {
				editor.putInt(key, (int) value);
			} else if (value instanceof Long) {
				editor.putLong(key, (long) value);
			} else if (value instanceof String) {
				editor.putString(key, (String) value);
			} else if (value instanceof Float) {
				editor.putFloat(key, (float) value);
			} else if (value instanceof Boolean) {
				editor.putBoolean(key, (boolean) value);
			} else {
				throw new IllegalArgumentException("There is a unsupported value object!");
			}
		}
		editor.apply();
	}

	public String getString(String key, String defValue) {
		return sp.getString(key, defValue);
	}

	public int getInt(String key, int defValue) {
		return sp.getInt(key, defValue);
	}

	public long getLong(String key, long defValue) {
		return sp.getLong(key, defValue);
	}

	public boolean getBoolean(String key, boolean defValue) {
		return sp.getBoolean(key, defValue);
	}

	public float getFloat(String key, float defValue) {
		return sp.getFloat(key, defValue);
	}
}
