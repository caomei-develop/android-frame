package com.huadongmedia.common.util;

import java.util.Observable;
import java.util.Observer;

import com.huadongmedia.common.support.LifeCycleAware;

public class ObservableUtil {

	public static void addObserver(final Observable observable, final Observer observer, LifeCycleAware lifeCycleAware) {
		observable.addObserver(observer);

		lifeCycleAware.addListener(new LifeCycleAware.Listener() {
			@Override
			public void onDestroy() {
				observable.deleteObserver(observer);
			}
		});
	}
}
