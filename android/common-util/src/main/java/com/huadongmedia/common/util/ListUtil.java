package com.huadongmedia.common.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

	@SuppressWarnings("unchecked")
	public static <K, V> List<K> keyList(List<V> inputList, String keyProp) {
		List<K> result = new ArrayList<K>();
		try {
			if (inputList != null) {
				String methodName = "get" + keyProp.substring(0, 1).toUpperCase() + keyProp.substring(1);
				for (V obj : inputList) {
					result.add((K) obj.getClass().getMethod(methodName).invoke(obj));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
