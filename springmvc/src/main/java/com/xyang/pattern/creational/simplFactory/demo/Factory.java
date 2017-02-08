package com.xyang.pattern.creational.simplFactory.demo;

public class Factory {
	public static Api createApi() {
		return new ApiImpl();
	}
}
