package com.cashtoutiao;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;

import com.huadongmedia.common.view.activity.BaseActivity;

public class SplashActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		ButterKnife.bind(this);
		startActivity(new Intent(this, MainActivity.class));
	}

	@Override
	public boolean hasToolbar() {
		return false;
	}
}
