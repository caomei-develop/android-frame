package com.cashtoutiao.common.api;

import android.util.Log;

import com.cashtoutiao.account.service.UserLocalService;
import com.cashtoutiao.common.api.bean.request.Request;
import com.cashtoutiao.common.api.constant.APIConstant;
import com.cashtoutiao.common.api.support.ApiEncrypter;
import com.cashtoutiao.common.api.support.Callback;
import com.huadongmedia.common.BaseApplication;
import com.huadongmedia.common.network.NetworkExecutor;
import com.huadongmedia.common.network.constant.HttpStatus;
import com.huadongmedia.common.network.support.CallCancelable;
import com.huadongmedia.common.network.support.NetworkCallback;
import com.huadongmedia.common.support.Cancelable;
import com.huadongmedia.common.util.AppUtil;
import com.huadongmedia.common.util.JSONUtil;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class ApiExecuter {

	public static <T> Cancelable post(String endpoint, Request request, boolean encrypted, final Callback<T> callBack) {
		try {
			// 设置基本的请求属性
			UserLocalService userLocalService = UserLocalService.instance();
			request.setUserId(userLocalService.getUserId());
			request.setAppVersionCode(AppUtil.getVersionCode(BaseApplication.context));
			if (userLocalService.getUserId() != null && userLocalService.getLoginId() != null) {
				endpoint = String.format("%s?userId=%d&loginId=%s", endpoint, userLocalService.getUserId(), userLocalService.getLoginId());
			}

			// 构建OkHttp网络请求参数
			com.squareup.okhttp.Request.Builder builder = new com.squareup.okhttp.Request.Builder();
			builder.url(endpoint);
			if (encrypted) {
				builder.header("Accept", "application/encrypted-json");
				builder.post(RequestBody.create(APIConstant.MEDIA_TYPE_ENCRYPTED_JSON, ApiEncrypter.encrypt(JSONUtil.toJSONBytes(request))));
			} else {
				builder.post(RequestBody.create(APIConstant.MEDIA_TYPE_JSON, JSONUtil.toJSONBytes(request)));
			}

			Cancelable result = NetworkExecutor.execute(builder.build(), callBack == null ? null : new NetworkCallback() {
				@Override
				public boolean isAsync() {
					return callBack.isAsync();
				}

				@Override
				public void onSuccess(Response response, byte[] body) {
					processResponse(response, body, null, null, callBack);
				}

				@Override
				public void onError(Integer state, String msg) {
					processResponse(null, null, state, msg, callBack);
				}
			});
			return result;
		} catch (Exception e) {
			// 这个异常基本不会发生，默认不处理
			Log.e(LOG_TAG, "unprocessed exception", e);
		}
		return new CallCancelable(null);
	}

	private static <T> void processResponse(Response response, byte[] body, Integer state, String msg, Callback<T> callBack) {
		// 看看是不是不需要回调
		if (callBack == null) {
			return;
		}

		T result = null;
		try {
			if (response != null) {
				state = HttpStatus.SUCCESS;
				if (response.body().contentType() != null && response.body().contentType().equals(APIConstant.MEDIA_TYPE_ENCRYPTED_JSON)) {
					body = ApiEncrypter.decrypt(body);
				}
				result = JSONUtil.parseObject(body, callBack.getType());
			}
		} catch (Exception e1) {
			state = HttpStatus.NETWORK_IO_EXCEPTION;
			msg = "网络请求异常，请稍后再试！";
		}

		if (result != null) {
			callBack.onSuccess(result);
		} else {
			callBack.onError(state, msg);
		}
	}

	private static final String LOG_TAG = "ApiExecuter";
}
