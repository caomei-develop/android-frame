package com.cashtoutiao;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import com.cashtoutiao.me.fragment.MeFragment;
import com.cashtoutiao.news.fragment.ToutiaoFragment;
import com.cashtoutiao.task.fragment.TaskFragment;
import com.cashtoutiao.video.fragment.VideoFragment;
import com.huadongmedia.common.util.CompatUtil;
import com.huadongmedia.common.view.activity.TabActivity;

public class MainActivity extends TabActivity {

	private List<TabItem> tabItemList = new ArrayList<>();

	private List<PagerFragment> viewPagerFragmentList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initTabBar();
		initViewPager();
	}

	private void initTabBar() {
		tabItemList.add(new TabItem("头条", CompatUtil.getDrawable(this, R.drawable.tabbar_toutiao_normal_icon), CompatUtil.getDrawable(this,
				R.drawable.tabbar_toutiao_selected_icon), false));
		tabItemList.add(new TabItem("视频", CompatUtil.getDrawable(this, R.drawable.tabbar_video_normal_icon), CompatUtil.getDrawable(this,
				R.drawable.tabbar_video_selected_icon), false));
		tabItemList.add(new TabItem("任务", CompatUtil.getDrawable(this, R.drawable.tabbar_invite_normal_icon), CompatUtil.getDrawable(this,
				R.drawable.tabbar_invite_selected_icon), false));
		tabItemList.add(new TabItem("我的", CompatUtil.getDrawable(this, R.drawable.tabbar_profile_normal_icon), CompatUtil.getDrawable(this,
				R.drawable.tabbar_profile_selected_icon), false));
		setTabItemList(tabItemList);
	}

	private void initViewPager() {
		Bundle bundle = new Bundle();
		bundle.putString("title", "One");
		viewPagerFragmentList.add(new PagerFragment(ToutiaoFragment.class, bundle));
		bundle = new Bundle();
		bundle.putString("title", "Two");
		viewPagerFragmentList.add(new PagerFragment(VideoFragment.class, bundle));
		bundle = new Bundle();
		bundle.putString("title", "Three");
		viewPagerFragmentList.add(new PagerFragment(TaskFragment.class, bundle));
		bundle = new Bundle();
		bundle.putString("title", "Four");
		viewPagerFragmentList.add(new PagerFragment(MeFragment.class, bundle));
		setPagerFragmentList(viewPagerFragmentList);
	}

	@Override
	public boolean hasToolbar() {
		return false;
	}
}
