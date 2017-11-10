package com.cashtoutiao.nativead.bean.adinfo;

import java.util.List;

import android.view.View;

import com.cashtoutiao.nativead.bean.strategy.AdPosition;

abstract public class NativeAdInfo {

	private AdPosition adPosition;

	private int exposureCount = 0;

	private int clickCount = 0;

	private long requestTime = System.currentTimeMillis();

	public boolean onExposure(View view) {
		return ++exposureCount <= 1;
	}

	public boolean onClick(View view) {
		return ++clickCount <= 1;
	}

	public boolean isExposured() {
		return exposureCount == 0;
	}

	public boolean isClicked() {
		return clickCount == 0;
	}

	public boolean isExpired() {
		return requestTime + 30 * 60 * 1000 < System.currentTimeMillis();
	}

	public String getSingleImage() {
		List<String> imgList = getImgList();
		if (imgList == null || imgList.isEmpty()) {
			return null;
		}
		return getImgList().get(0);
	}

	abstract public int getDisplayType();

	abstract public String getTitle();

	abstract public List<String> getImgList();

	abstract public boolean isDownloadApp();

	abstract public String getPackageName();

	abstract public boolean isAppExist();
}
