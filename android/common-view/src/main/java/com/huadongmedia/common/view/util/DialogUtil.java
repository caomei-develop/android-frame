package com.huadongmedia.common.view.util;

import com.huadongmedia.common.support.Cancelable;
import com.huadongmedia.common.support.LifeCycleAware;

import android.app.Dialog;
import android.content.DialogInterface;

public class DialogUtil {

	public static void show(Dialog dialog, LifeCycleAware lifeCycleAware) {
		show(dialog, lifeCycleAware, null);
	}

	public static void show(final Dialog dialog, final LifeCycleAware lifeCycleAware, final Cancelable cancelable) {
		final LifeCycleAware.Listener lifeCycleListener = new LifeCycleAware.Listener() {
			@Override
			public void onDestroy() {
				try {
					dialog.dismiss();
				} catch (Exception e) {
				}
			}
		};
		dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				try {
					lifeCycleAware.removeListener(lifeCycleListener);
					// cancel本生应该没有什么副作用
					cancelable.cancel();
				} catch (Exception e) {
				}
			}
		});
		lifeCycleAware.addListener(lifeCycleListener);
		dialog.show();
	}

}
