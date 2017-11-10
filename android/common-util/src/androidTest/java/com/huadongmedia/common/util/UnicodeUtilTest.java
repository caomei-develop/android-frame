package com.huadongmedia.common.util;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UnicodeUtilTest {

	@Test
	public void toUnicode() throws Exception {
		String inputStr = "\\u597d你好吗?";
		String unicodeStr = UnicodeUtil.toUnicode(inputStr);
		System.out.println(unicodeStr);
		String originalStr = UnicodeUtil.parseUnicode(unicodeStr);
		System.out.println(originalStr);
	}
}
