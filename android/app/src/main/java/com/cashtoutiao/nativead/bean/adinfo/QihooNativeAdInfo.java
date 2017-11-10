package com.cashtoutiao.nativead.bean.adinfo;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.view.View;

import com.ak.android.engine.nav.NativeAd;
import com.cashtoutiao.nativead.bean.AdDisplayType;
import com.cashtoutiao.nativead.util.TitleUtil;
import com.huadongmedia.common.util.StringUtil;

public class QihooNativeAdInfo extends NativeAdInfo {

	private NativeAd nativeAd;

	public QihooNativeAdInfo(NativeAd nativeAd) {
		this.nativeAd = nativeAd;
	}

	@Override
	public boolean onExposure(View view) {
		boolean flag = super.onExposure(view);
		if (flag) {
			nativeAd.onAdShowed(view);
		}
		return flag;
	}

	@Override
	public boolean onClick(View view) {
		boolean flag = super.onClick(view);
		nativeAd.onAdClick((Activity) view.getContext(), view);
		return flag;
	}

	@Override
	public int getDisplayType() {
		return AdDisplayType.SMALL_IMAGE;
	}

	@Override
	public String getTitle() {
		return TitleUtil.chooseLonger(getAttr("title"), getAttr("ext_text"));
	}

	@Override
	public List<String> getImgList() {
		if (nativeAd != null) {
			String image = getAttr("contentimg");
			if (!StringUtil.isEmpty(image)) {
				return Arrays.asList(image);
			}
		}
		return null;
	}

	@Override
	public boolean isDownloadApp() {
		if (nativeAd == null) {
			return false;
		}
		return nativeAd.getActionType() == 1;
	}

	private String getAttr(String attr) {
		try {
			JSONObject ad = nativeAd.getContent();
			return ad.getString(attr);
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public String getPackageName() {
		try {
			return nativeAd.getAPPInfo().getString("app_pkg");
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public boolean isAppExist() {
		// TODO 暂时写成1，是错的，要查文档修改
		return nativeAd.getAPPStatus() == 1;
	}
}
