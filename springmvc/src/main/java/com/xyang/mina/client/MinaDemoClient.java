package com.xyang.mina.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaDemoClient {
	private static final int port = 8080;
	private static IoConnector connector;
	private static IoSession ioSession;

	public static void main(String[] args) throws IOException {
		connector = new NioSocketConnector();
		// 1.设置编码过滤器
		connector.getFilterChain().addLast("coderc",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
						LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));
		connector.getFilterChain().addFirst("filter", new MyClientDemoFilter());
		connector.setHandler(new MyClientDemoHandler());
		ConnectFuture connectFuture = connector.connect(new InetSocketAddress(port));
		connectFuture.awaitUninterruptibly();// 等待连接

		ioSession = connectFuture.getSession();
		ioSession.write("你好 -- Hello world!");

		ioSession.getCloseFuture().awaitUninterruptibly();// 等待关闭连接
		connector.dispose();
	}
}
