package com.huadongmedia.common.view.fragment;

import com.huadongmedia.common.support.LifeCycleAware;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

	protected LifeCycleAware lifeCycleAware;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		lifeCycleAware = new LifeCycleAware(getActivity());
	}

	@Override
	public void onStart() {
		lifeCycleAware.onStart();
		super.onStart();
	}

	@Override
	public void onStop() {
		lifeCycleAware.onStop();
		super.onStop();
	}

	@Override
	public void onResume() {
		lifeCycleAware.onResume();
		super.onResume();
	}

	@Override
	public void onPause() {
		lifeCycleAware.onPause();
		super.onPause();
	}

	@Override
	public void onDestroy() {
		lifeCycleAware.onDestroy();
		super.onDestroy();
	}
}
