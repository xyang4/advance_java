package com.xyang.utils;

public class HttpRequestResult{
	private int code;// 返回状态码
	private String content;// 返回内容

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
