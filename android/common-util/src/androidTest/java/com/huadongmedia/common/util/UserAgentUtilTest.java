package com.huadongmedia.common.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class UserAgentUtilTest {

	@Rule
	public UiThreadTestRule rule = new UiThreadTestRule();

	@Test
	@UiThreadTest
	public void getUserAgent() throws Exception {
		System.out.println(UserAgentUtil.getUserAgent());
	}
}