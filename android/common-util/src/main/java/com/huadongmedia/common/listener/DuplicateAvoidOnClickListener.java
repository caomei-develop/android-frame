package com.huadongmedia.common.listener;

import android.view.View;

abstract class DuplicateAvoidOnClickListener implements View.OnClickListener {

	private long lastClickTime = 0L;

	@Override
	public void onClick(View v) {
		if (System.currentTimeMillis() > 1000 + lastClickTime) {
			doClick(v);
		}
		lastClickTime = System.currentTimeMillis();
	}

	abstract public void doClick(View v);
}
