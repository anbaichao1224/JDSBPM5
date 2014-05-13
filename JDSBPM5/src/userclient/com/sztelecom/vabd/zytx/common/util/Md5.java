package com.sztelecom.vabd.zytx.common.util;

import java.security.*;

/**
 * @Title: Md5�㷨ʵ��
 * @Description: ׿Խͨ��
 * @Copyright: Copyright (c) 2007
 * @Company: ���ڵ�����ֵҵ��
 * @author л�࿥
 * @version 1.0
 */

public class Md5 {
	/**
	 * @���ܣ�����������ַ�����MD5
	 * @���룺s,��Ҫ���ܵ��ַ���;
	 * @��������ַ�����MD5��
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
	}
}