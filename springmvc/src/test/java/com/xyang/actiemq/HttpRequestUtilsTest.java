package com.xyang.actiemq;

import org.junit.Test;

import com.xyang.utils.HttpRequestResult;
import com.xyang.utils.HttpRequestUtils;

public class HttpRequestUtilsTest {

	@Test
	public void test() {
		HttpRequestResult result = HttpRequestUtils.doGet("http://www.baidu.com",null);
		System.out.println(result.getCode());
	}

}
