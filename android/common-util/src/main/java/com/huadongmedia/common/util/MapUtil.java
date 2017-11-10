package com.huadongmedia.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {

	public static Map<Object, Object> build(Object... keyValues) {
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		for (int i = 0; i < keyValues.length; i += 2) {
			resultMap.put(keyValues[i], keyValues[i + 1]);
		}
		return resultMap;
	}

	public static Map<String, String> build(String... keyValues) {
		Map<String, String> resultMap = new HashMap<String, String>();
		for (int i = 0; i < keyValues.length; i += 2) {
			resultMap.put(keyValues[i], keyValues[i + 1]);
		}
		return resultMap;
	}

	public static void build(Map<String, String> resultMap, String... keyValues) {
		for (int i = 0; i < keyValues.length; i += 2) {
			resultMap.put(keyValues[i], keyValues[i + 1]);
		}
	}

	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> mapObject(List<V> inputList, String keyProp) {
		Map<K, V> result = new HashMap<K, V>();
		try {
			if (inputList != null) {
				String methodName = "get" + keyProp.substring(0, 1).toUpperCase() + keyProp.substring(1);
				for (V obj : inputList) {
					result.put((K) obj.getClass().getMethod(methodName).invoke(obj), obj);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, List<V>> mapList(List<V> inputList, String keyProp) {
		Map<K, List<V>> result = new HashMap<K, List<V>>();
		try {
			if (inputList != null) {
				String methodName = "get" + keyProp.substring(0, 1).toUpperCase() + keyProp.substring(1);
				for (V obj : inputList) {
					K key = (K) obj.getClass().getMethod(methodName).invoke(obj);
					List<V> itemList = result.get(key);
					if (itemList == null) {
						itemList = new ArrayList<V>();
						result.put(key, itemList);
					}
					itemList.add(obj);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
