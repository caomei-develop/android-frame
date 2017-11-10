package com.huadongmedia.common.support;

import java.lang.ref.WeakReference;

import android.app.Activity;

public class TopActivityAware {

	private static WeakReference<Activity> topActivityRef;

	public static void set(Activity activity) {
		topActivityRef = new WeakReference<Activity>(activity);
	}

	public static void clear(Activity activity) {
		if (topActivityRef != null && activity == topActivityRef.get()) {
			topActivityRef = null;
		}
	}

	public static Activity top() {
		if (topActivityRef == null) {
			return null;
		}
		return topActivityRef.get();
	}
}
