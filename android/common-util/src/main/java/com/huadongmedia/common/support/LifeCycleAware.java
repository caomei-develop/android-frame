package com.huadongmedia.common.support;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;

public class LifeCycleAware {

	private boolean started;

	private boolean resumed;

	private boolean destroyed;

	private List<Listener> listenerList = new LinkedList<>();

	private Activity activity;

	public LifeCycleAware() {
	}

	public LifeCycleAware(Activity activity) {
		this.activity = activity;
	}

	public boolean isStarted() {
		return started;
	}

	public boolean isResumed() {
		return resumed;
	}

	public boolean isFinished() {
		if (activity == null) {
			return destroyed;
		}
		return activity.isFinishing();
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void onStart() {
		started = true;
	}

	public void onStop() {
		started = false;
	}

	public void onResume() {
		resumed = true;
	}

	public void onPause() {
		resumed = false;
	}

	public void onDestroy() {
		destroyed = true;
		for (Listener listener : listenerList) {
			listener.onDestroy();
		}
	}

	public void addListener(Listener listener) {
		listenerList.add(listener);
	}

	public void removeListener(Listener listener) {
		listenerList.remove(listener);
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public static abstract class Listener {

		public void onDestroy() {
		}
	}
}
