package com.huadongmedia.common.util;

public class UnicodeUtil {

	public static String toUnicode(String inputStr) {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < inputStr.length(); ++i) {
			char c = inputStr.charAt(i);
			if (c >= 128) {
				String strHex = Integer.toHexString(c);
				strBuf.append("\\u" + strHex);
			} else {
				strBuf.append(c);
			}
		}
		return strBuf.toString();
	}

	public static String parseUnicode(String inputStr) {
		StringBuffer strBuf = new StringBuffer();
		int index = 0;
		boolean escape = false;
		while (index < inputStr.length()) {
			char c = inputStr.charAt(index);
			switch (c) {
				case '\\': {
					if (escape) {
						strBuf.append("\\\\");
						escape = false;
					} else {
						escape = true;
					}
					break;
				}
				case 'u': {
					if (escape) {
						if (index + 4 < inputStr.length()) {
							strBuf.append((char) (int) Integer.valueOf(inputStr.substring(index + 1, index + 5), 16));
							index += 4;
						} else {
							strBuf.append("\\u");
						}
						escape = false;
					} else {
						strBuf.append('u');
					}
					break;
				}
				default: {
					strBuf.append(c);
				}
			}
			++index;
		}
		return strBuf.toString();
	}
}
