package com.xyang.mina;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.xyang.mina.protocal.ProtocalFactoryImpl;

public class ProtocalServer {
	private static final int port = 8080;

	public static void main(String[] args) throws IOException {
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addFirst("codec",new ProtocolCodecFilter(new ProtocalFactoryImpl(Charset.forName("UTF-8"))));
		acceptor.getSessionConfig().setReadBufferSize(1024);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		acceptor.setHandler(new ProtocalHandler());
		acceptor.bind(new InetSocketAddress(port));
		System.out.println("Server started!");
	}

	static class ProtocalHandler extends IoHandlerAdapter {
		@Override
		public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
			throw new Exception("异常", cause);
		}
		@Override
		public void messageReceived(IoSession session, Object message) throws Exception {
			System.out.printf("接受到的数据:", message);
		}

		@Override
		public void messageSent(IoSession session, Object message) throws Exception {
			System.out.println("发送的数据:" + message);
		}
	}
}