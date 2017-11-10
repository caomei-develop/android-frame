package com.huadongmedia.common.network;

import java.io.IOException;
import java.util.Map;

import com.huadongmedia.common.network.constant.HttpStatus;
import com.huadongmedia.common.network.support.CallCancelable;
import com.huadongmedia.common.network.support.NetworkCallback;
import com.huadongmedia.common.support.Cancelable;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class NetworkExecutor {

	private final static OkHttpClient client = new OkHttpClient();

	public static Cancelable execute(Request request, final NetworkCallback callback) {
		try {
			final Call call = client.newCall(request);
			final Cancelable result = new CallCancelable(call);
			if (callback != null && !callback.isAsync()) {
				// 执行同步网络请求
				try {
					Response response = call.execute();
					processResponse(response, null, callback, result, null);
				} catch (IOException e) {
					processResponse(null, e, callback, result, null);
				}
			} else {
				// 执行异步网络请求
				final Handler handler = Looper.myLooper() != null ? new Handler() : null;
				call.enqueue(new com.squareup.okhttp.Callback() {
					@Override
					public void onFailure(com.squareup.okhttp.Request request, IOException e) {
						processResponse(null, e, callback, result, handler);
					}

					@Override
					public void onResponse(Response response) throws IOException {
						processResponse(response, null, callback, result, handler);
					}
				});
			}
			return result;
		} catch (Exception e) {
			// 这个异常基本不会发生，默认不处理
			Log.e(LOG_TAG, "unprocessed exception", e);
		}
		return new CallCancelable(null);
	}

	public static void getAsync(String endpoint, Map<String, String> headers) {
		com.squareup.okhttp.Request.Builder builder = new com.squareup.okhttp.Request.Builder();
		builder.url(endpoint);
		if (headers != null) {
			builder.headers(Headers.of(headers));
		}
		execute(builder.build(), null);
	}

	private static void processResponse(Response response, IOException e, final NetworkCallback callback, Cancelable cancelable, Handler handler) {
		int state;
		String msg = null;
		byte[] body = null;

		try {
			if (e != null) {
				state = HttpStatus.NETWORK_IO_EXCEPTION;
				msg = "网络请求异常，请稍后再试！";
				Log.e(LOG_TAG, "processResponse exception", e);
			} else {
				if (response.code() != HttpStatus.SUCCESS) {
					state = response.code();
					msg = response.message();
				} else {
					state = HttpStatus.SUCCESS;
					body = response.body().bytes();
				}
			}
		} catch (Exception e1) {
			state = HttpStatus.NETWORK_IO_EXCEPTION;
			msg = "网络请求异常，请稍后再试！";
		}

		// 看看是不是不需要回调，或者已经被取消
		if (callback == null || (cancelable != null && cancelable.isCanceled())) {
			return;
		}

		Runnable runnable = new PostRunnable(state, msg, response, body) {
			@Override
			public void run() {
				if (state == HttpStatus.SUCCESS) {
					callback.onSuccess(response, body);
				} else {
					callback.onError(state, msg);
				}
			}
		};

		if (handler == null) {
			runnable.run();
		} else {
			handler.post(runnable);
		}
	}

	private static class PostRunnable implements Runnable {

		public int state;

		public String msg = null;

		public Response response;

		public byte[] body;

		public PostRunnable(int state, String msg, Response response, byte[] body) {
			this.state = state;
			this.msg = msg;
			this.response = response;
			this.body = body;
		}

		@Override
		public void run() {
		}
	}

	private static final String LOG_TAG = "NetworkExecutor";
}
