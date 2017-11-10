package com.huadongmedia.common.view.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.huadongmedia.common.support.LifeCycleAware;
import com.huadongmedia.common.support.TopActivityAware;
import com.huadongmedia.common.view.R;
import com.huadongmedia.common.view.compoment.Toolbar;

public class BaseActivity extends AppCompatActivity {

	protected LifeCycleAware lifeCycleAware;

	protected Toolbar toolbar;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lifeCycleAware = new LifeCycleAware(this);
	}

	@Override
	public void setContentView(@LayoutRes int layoutResID) {
		if (hasToolbar()) {
			super.setContentView(R.layout.activity_base);
			FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_layout);
			getLayoutInflater().inflate(layoutResID, contentFrameLayout, true);
			initToolbar();
		} else {
			super.setContentView(layoutResID);
		}
	}

	@Override
	protected void onStart() {
		lifeCycleAware.onStart();
		super.onStart();
	}

	@Override
	protected void onStop() {
		lifeCycleAware.onStop();
		super.onStop();
	}

	@Override
	protected void onResume() {
		lifeCycleAware.onResume();
		// 设置TopActivityAware
		TopActivityAware.set(this);
		super.onResume();
	}

	@Override
	protected void onPause() {
		lifeCycleAware.onPause();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		lifeCycleAware.onDestroy();
		// 及早释放TopActivityAware
		TopActivityAware.clear(this);
		super.onDestroy();
	}

	public boolean hasToolbar() {
		return true;
	}

	public void initToolbar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		if (toolbar != null) {
			if (getTitle() != null) {
				toolbar.setTitle(getTitle());
			}
			toolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}
	}

	public LifeCycleAware getLifeCycleAware() {
		return lifeCycleAware;
	}
}
