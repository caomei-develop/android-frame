package com.huadongmedia.common.network.support;

import com.huadongmedia.common.callback.AsyncCallbacks;
import com.squareup.okhttp.Response;

public class NetworkCallback extends AsyncCallbacks.TwoTwo<Response, byte[], Integer, String> {

	private boolean async = true;

	public NetworkCallback() {
	}

	public NetworkCallback(boolean async) {
		this.async = async;
	}

	public boolean isAsync() {
		return async;
	}

	public void onSuccess(Response response, byte[] body) {
	}

	@Override
	public void onError(Integer state, String msg) {
	}
}
