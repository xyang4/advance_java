package com.xyang.mina.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xyang.mina.client.MinaDemoClient;

public class MinaDemoServer {

	private static final Logger logger = LoggerFactory.getLogger(MinaDemoClient.class);
	private static final int port = 8080;
	private static IoAcceptor accept;

	public static void main(String[] args) throws IOException {
		
		// 0.processor以NIO方式提供一个多线程环境，供filter读写原数据，由NioSocketAcceptor构造器实现
		accept = new NioSocketAcceptor();
		// 1.设置编码过滤器
		accept.getFilterChain().addLast("coderc",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
						LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));
		accept.getFilterChain().addFirst("filter", new MyServerDemoFilter());

		// 2.处理器设置
		accept.setHandler(new MyServerDemoHandler());

		// 3.通过sessionConfig进行会话相关设置，如超时时间、缓存区大小等等
		accept.getSessionConfig().setMaxReadBufferSize(1024);
		accept.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

		accept.bind(new InetSocketAddress(port));
		logger.info("Server ---> started ok!");
	}
}
