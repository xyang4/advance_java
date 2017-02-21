package com.xyang;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpclientTest {
	@Test
	public void baseTest() throws IOException {
		String request_url = "https://www.taobao.com/";
		CloseableHttpClient httpclient = HttpClients.createDefault(); // 创建httpclient实例
		HttpGet httpget = new HttpGet(request_url); // 创建httpget实例
		// 1.通过设置 header可模拟诸如登录等操作，通过使用代理ip避免爬虫限制
		// 代理分为：透明代理、匿名代理、混淆代理、高匿代理【优先选择】
		HttpHost proxy = new HttpHost("106.46.136.64", 808);
		RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
		// httpget.setConfig(requestConfig);
		httpget.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");

		// 2. 响应信息处理
		CloseableHttpResponse response = httpclient.execute(httpget); // 执行get请求
		HttpEntity entity = response.getEntity(); // 获取返回实体
		System.out.println("网页内容：" + EntityUtils.toString(entity, "utf-8")); // 指定编码打印网页内容
		response.close(); // 关闭流和释放系统资源
		httpclient.close();
	}

	@Test
	public void act_test() {
		AtomicInteger ai = new AtomicInteger();
		long no = Runtime.getRuntime().totalMemory();
		System.out.println(ai.intValue());
	}
}
