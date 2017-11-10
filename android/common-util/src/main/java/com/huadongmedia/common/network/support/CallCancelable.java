package com.huadongmedia.common.network.support;

import com.huadongmedia.common.support.Cancelable;
import com.squareup.okhttp.Call;

public class CallCancelable implements Cancelable {

	private Call call;

	public CallCancelable(Call call) {
		this.call = call;
	}

	@Override
	public void cancel() {
		if (call != null) {
			call.cancel();
		}
	}

	@Override
	public boolean isCanceled() {
		if (call != null) {
			return call.isCanceled();
		}
		return false;
	}
}
