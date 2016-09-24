package com.xyang.utils;

import java.io.UnsupportedEncodingException;

public class CodingUtils {
	/**
	 * @描述 把中文字符转换为带百分号的浏览器编码
	 * @param word
	 *            中文字符
	 * @param encoding
	 *            字符编码
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String toBrowserCode(String word, String encoding)
			throws UnsupportedEncodingException {
		encoding=null==encoding?"gb2312":encoding;
		byte[] textByte = word.getBytes(encoding);
		StringBuilder strBuilder = new StringBuilder();

		for (int j = 0; j < textByte.length; j++) {
			// 转换为16进制字符
			String hexStr = Integer.toHexString(textByte[j] & 0xff);
			strBuilder.append("%" + hexStr.toUpperCase());
		}
		return strBuilder.toString();
	}
}
