package com.huadongmedia.common.util;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;

public class CipherUtil {

	public static byte[] encrypt(Key key, byte[] src, String algorithm) {
		try {
			Cipher c1 = Cipher.getInstance(algorithm);
			c1.init(Cipher.ENCRYPT_MODE, key);
			return c1.doFinal(src);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] decrypt(Key key, byte[] src, String algorithm) {
		try {
			Cipher c1 = Cipher.getInstance(algorithm);
			c1.init(Cipher.DECRYPT_MODE, key);
			return c1.doFinal(src);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] decrypt(Key key, byte[] src, String algorithm, AlgorithmParameterSpec algorithmParameterSpec)
			throws Exception {
		try {
			Cipher c1 = Cipher.getInstance(algorithm);
			c1.init(Cipher.DECRYPT_MODE, key, algorithmParameterSpec);
			return c1.doFinal(src);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] mapping(byte[] dict, byte[] src) {
		return mapping(dict, src, true);
	}

	public static byte[] mapping(byte[] dict, byte[] src, boolean inPlace) {
		if (dict.length != 256) {
			throw new IllegalArgumentException("The length of dict should be 256");
		}
		byte[] result = inPlace ? src : new byte[src.length];
		for (int i = 0; i < src.length; ++i) {
			result[i] = dict[src[i] + 128];
		}
		return result;
	}
}
