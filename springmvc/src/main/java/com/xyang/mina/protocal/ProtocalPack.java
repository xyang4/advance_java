package com.xyang.mina.protocal;

import org.apache.commons.lang.StringUtils;

public class ProtocalPack {
	private int length;
	private byte flag;
	private String content;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public ProtocalPack(byte flag, String content) {
		this.flag = flag;
		this.content = content;
		this.length = 4 + 1 + (StringUtils.isBlank(content) ? 0 : content.length());// int
																					// 4
																					// byte+byte
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ProtocalPack [length=" + length + ", flag=" + flag + ", content=" + content + "]";
	}
}
