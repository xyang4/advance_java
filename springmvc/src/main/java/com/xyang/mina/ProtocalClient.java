package com.xyang.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.xyang.mina.protocal.ProtocalFactoryImpl;
import com.xyang.mina.protocal.ProtocalPack;

public class ProtocalClient {
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 8080;
	private static int loopNum = 100;

	public static void main(String[] args) {
		IoConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("codec",new ProtocolCodecFilter(new ProtocalFactoryImpl(Charset.forName("UTF-8"))));
		connector.getSessionConfig().setReadBufferSize(1024);
		connector.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		connector.setHandler(new MyHandler());
		ConnectFuture connetfutrue = connector.connect(new InetSocketAddress(HOST, PORT));
		connetfutrue.addListener(new IoFutureListener<ConnectFuture>() {
			@Override
			public void operationComplete(ConnectFuture future) {
				if (future.isConnected()) {
					IoSession session = future.getSession();
					sentData(session);
				}
			}
			/**
			 * 发送数据
			 */
			private void sentData(IoSession session) {
				for (int i = 0; i < loopNum; i++) {
					ProtocalPack pp = new ProtocalPack((byte) i, "xyang" + i);
					//System.out.println("客户端发送数据:" + pp);
					session.write(pp);
				}
			}
		});
	}

	private static class MyHandler extends IoHandlerAdapter {
		@Override
		public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
			System.out.println("异常！");
		}

		@Override
		public void messageReceived(IoSession session, Object message) throws Exception {
			ProtocalPack pack = (ProtocalPack) message;
			System.out.println("接收到的数据: " + pack);
		}

		@Override
		public void messageSent(IoSession session, Object message) throws Exception {
			ProtocalPack pack = (ProtocalPack) message;
			System.out.println("发送的数据: " + pack);
		}
		@Override
		public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
			if (IdleStatus.READER_IDLE == status) {
				session.close();
			}
		}
	}
}
