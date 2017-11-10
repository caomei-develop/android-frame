package com.cashtoutiao.common.api.support;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.huadongmedia.common.callback.AsyncCallbacks;

public class Callback<T> extends AsyncCallbacks.OneTwo<T, Integer, String> {

	private boolean async = true;

	public Callback() {
	}

	public Callback(boolean async) {
		this.async = async;
	}

	public final Type getType() {
		return ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public boolean isAsync() {
		return async;
	}

	@Override
	public void onSuccess(T result) {
	}

	@Override
	public void onError(Integer state, String msg) {
	}
}
