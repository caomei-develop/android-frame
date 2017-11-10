package com.huadongmedia.common.callback;

import com.huadongmedia.common.BaseApplication;
import com.huadongmedia.common.support.LifeCycleAware;

import android.app.Dialog;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public abstract class UISafeCallback {

	public static abstract class OneResult<R> extends AsyncCallbacks.OneTwo<R, Integer, String> {

		private LifeCycleAware recycleAware;

		private Dialog loadingDialog;

		public OneResult(LifeCycleAware recycleAware) {
			this.recycleAware = recycleAware;
		}

		public OneResult(LifeCycleAware recycleAware, Dialog loadingDialog) {
			this.recycleAware = recycleAware;
			this.loadingDialog = loadingDialog;
		}

		@Override
		public final void onSuccess(final R result) {
			try {
				if (loadingDialog != null) {
					loadingDialog.dismiss();
				}
				if (recycleAware == null || recycleAware.isFinished() || recycleAware.isDestroyed()) {
					return;
				}
				if (Looper.getMainLooper() == Looper.myLooper()) {
					doSuccess(result);
				} else {
					new Handler(Looper.getMainLooper()).post(new Runnable() {
						@Override
						public void run() {
							try {
								doSuccess(result);
							} catch (Exception e) {
								Log.e(LOG_TAG, "post doSuccess exception", e);
							}
						}
					});
				}
			} catch (Exception e) {
				Log.e(LOG_TAG, "onSuccess exception", e);
			}
		}

		@Override
		public final void onError(final Integer state, final String msg) {
			try {
				if (loadingDialog != null) {
					loadingDialog.dismiss();
				}
				if (recycleAware == null || recycleAware.isFinished() || recycleAware.isDestroyed()) {
					return;
				}
				if (Looper.getMainLooper() == Looper.myLooper()) {
					doError(state, msg);
				} else {
					new Handler(Looper.getMainLooper()).post(new Runnable() {
						@Override
						public void run() {
							try {
								doError(state, msg);
							} catch (Exception e) {
								Log.e(LOG_TAG, "post doError exception", e);
							}
						}
					});
				}
			} catch (Exception e) {
				Log.e(LOG_TAG, "onError exception", e);
			}
		}

		public void doSuccess(R result) {
		}

		public void doError(Integer state, String msg) {
			Toast.makeText(BaseApplication.context, msg, Toast.LENGTH_SHORT).show();
		}
	}

	public static abstract class TwoResult<R1, R2> extends AsyncCallbacks.TwoTwo<R1, R2, Integer, String> {

		private LifeCycleAware recycleAware;

		private Dialog loadingDialog;

		public TwoResult(LifeCycleAware recycleAware) {
			this.recycleAware = recycleAware;
		}

		public TwoResult(LifeCycleAware recycleAware, Dialog loadingDialog) {
			this.recycleAware = recycleAware;
			this.loadingDialog = loadingDialog;
		}

		@Override
		public final void onSuccess(final R1 resultOne, final R2 resultTwo) {
			try {
				if (loadingDialog != null) {
					loadingDialog.dismiss();
				}
				if (recycleAware == null || recycleAware.isFinished() || recycleAware.isDestroyed()) {
					return;
				}
				if (Looper.getMainLooper() == Looper.myLooper()) {
					doSuccess(resultOne, resultTwo);
				} else {
					new Handler(Looper.getMainLooper()).post(new Runnable() {
						@Override
						public void run() {
							try {
								doSuccess(resultOne, resultTwo);
							} catch (Exception e) {
								Log.e(LOG_TAG, "post doSuccess exception", e);
							}
						}
					});
				}
			} catch (Exception e) {
				Log.e(LOG_TAG, "onSuccess exception", e);
			}
		}

		@Override
		public final void onError(final Integer state, final String msg) {
			try {
				if (loadingDialog != null) {
					loadingDialog.dismiss();
				}
				if (recycleAware == null || recycleAware.isFinished() || recycleAware.isDestroyed()) {
					return;
				}
				if (Looper.getMainLooper() == Looper.myLooper()) {
					doError(state, msg);
				} else {
					new Handler(Looper.getMainLooper()).post(new Runnable() {
						@Override
						public void run() {
							try {
								doError(state, msg);
							} catch (Exception e) {
								Log.e(LOG_TAG, "post doError exception", e);
							}
						}
					});
				}
			} catch (Exception e) {
				Log.e(LOG_TAG, "onError exception", e);
			}
		}

		public void doSuccess(R1 resultOne, R2 resultTwo) {
		}

		public void doError(Integer state, String msg) {
			Toast.makeText(BaseApplication.context, msg, Toast.LENGTH_SHORT).show();
		}
	}

	private static final String LOG_TAG = "UISafeCallback";
}
