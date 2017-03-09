package com.xyang.mina.server;

import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @描述 自定义filter
 * @date 2017年3月5日-上午9:57:56
 * @author IBM
 *
 */
public class MyServerDemoFilter extends IoFilterAdapter {
	private static final Logger logger = LoggerFactory.getLogger(MyServerDemoFilter.class);

	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
		logger.info("sessionID[{}] -->server receive msg[{}].", session.getId(), message);
		super.messageReceived(nextFilter, session, message);
	}

	@Override
	public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
		logger.info("sessionID[{}] -->server sent msg[{}].", session.getId(), writeRequest.getMessage());
		super.messageSent(nextFilter, session, writeRequest);
	}

}
