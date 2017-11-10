package com.cashtoutiao.common.api.support;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import com.huadongmedia.common.util.CipherUtil;

public class ApiEncrypter {

	public static byte[] encrypt(byte[] rawBytes) {
		byte[] aesBytes = CipherUtil.encrypt(key, rawBytes,
				"");
		return CipherUtil.mapping(encodeDic, aesBytes);
	}

	public static byte[] decrypt(byte[] encryptedBytes) {
		byte[] aesBytes = CipherUtil.mapping(decodeDic, encryptedBytes);
		return CipherUtil.decrypt(key, aesBytes, "");
	}

	private static String password = "";

	private static Key key = new SecretKeySpec(password.getBytes(), "AES");

	private static byte[] encodeDic = new byte[]{-95, -68, -34, 57, 106, 88,
			16, -36, -104, 43, 71, -108, 123, 9, -40, -39, -32, -27, 11, -24,
			107, 62, -1, 115, -118, -35, 113, -91, -115, -50, -127, -77, -119,
			79, -28, 126, -107, -112, 65, -97, 38, 87, 111, 120, -98, 101, 50,
			-71, -4, 28, 63, -43, 75, 99, -20, 53, -56, 8, -79, 124, 66, 54,
			-19, 60, -23, -55, 34, 81, -83, 33, -73, 27, 49, 24, -14, 18, -6,
			-63, -31, 73, 52, 7, 36, -7, -44, 0, -106, 12, 78, 31, 39, 30,
			-100, 4, -16, 114, -66, 103, 20, -11, 41, 21, -75, 22, -69, -78,
			-53, -60, -47, 90, -96, 59, 6, -62, -12, 95, 86, 85, -46, 17, -87,
			3, -67, 5, -30, 127, 51, -89, 56, -26, -8, 26, 105, -45, 68, 61,
			82, 40, 45, -37, -128, 112, 14, 104, 44, -10, 93, 13, 64, 92, -18,
			102, 2, 119, -117, -41, 122, -74, 109, 74, -33, -64, -103, -48, 76,
			47, -92, -110, -52, -123, -54, 70, 32, -114, -121, -82, 94, -102,
			-72, 42, 35, 84, -120, -99, 69, -65, -22, 97, -81, 77, -13, -101,
			-111, -84, -9, -61, 72, 121, 89, -76, -116, 117, -2, -38, 48, 83,
			-113, -21, 46, -126, -80, 19, 80, -17, 15, 125, -88, -85, -124, -5,
			-29, 25, -125, -90, -51, 55, -59, -42, -49, -70, 108, -3, -109, 58,
			1, -94, 67, 10, 118, 37, 110, 96, 23, -122, -15, -25, -86, -58, 91,
			-57, 100, -93, 116, -105, 29, 98};

	private static byte[] decodeDic = new byte[]{12, -98, 81, 94, 90, 41, 115,
			46, 54, -96, -104, 26, 72, -100, 45, 78, -91, 64, 39, 104, -117,
			-92, -42, 125, -120, 34, 49, 63, -36, 55, -84, -89, -18, -128, 107,
			123, 38, -101, 95, -1, 88, -8, 118, 89, 65, -60, 47, 60, 82, -70,
			-23, -97, 71, -26, 29, -58, 50, -81, 101, -24, -127, -6, -32, 57,
			33, -51, -15, 67, -21, 98, 119, 121, -72, -63, 42, -22, 40, 96,
			-99, 100, 35, -20, -10, 5, -44, -77, 99, 27, -114, -113, 75, 11,
			-121, -103, -126, 32, -112, -50, -4, 92, -94, -111, 1, 117, -109,
			-64, 58, 79, -74, -66, 22, 85, -34, 116, -54, 62, -14, -29, 17, 66,
			2, -45, -52, 91, -80, 103, 74, -106, -43, 106, 24, -7, -35, -5,
			-16, -47, -71, -115, 109, -110, -41, 19, 14, 86, -122, -9, -53, 83,
			-30, -27, -25, 114, -55, 93, 3, -57, -79, 126, -37, -39, 44, -59,
			-62, 52, -46, 111, -88, -38, 9, -28, 51, -119, 16, 10, 80, 37, 76,
			-56, -82, -2, -48, -73, -67, 97, 0, -125, 105, -17, -65, 7, -107,
			-78, 20, -90, -68, 108, 6, 56, 43, -118, 68, -49, 31, -76, 36, 61,
			-40, -95, 84, -61, 8, 77, 53, -11, -12, -87, -123, 70, -19, 120,
			21, 18, 48, -13, 113, 59, 127, -75, 122, -83, 23, -31, 15, 4, -124,
			-108, 102, 30, 112, -86, 13, -102, -33, -105, 124, 73, 110, 25,
			-85, 69, 28, -116, -69, 87, -93, -3};
}
