package com.xyang.mina.client;

import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyClientDemoFilter extends IoFilterAdapter  {
	private static final Logger logger = LoggerFactory.getLogger(MyClientDemoFilter.class);

	@Override
	public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
		logger.info("sessionID[{}] -->cilent receive msg[{}].", session.getId(), message);
		super.messageReceived(nextFilter, session, message);
	}

	@Override
	public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
		logger.info("sessionID[{}] -->client sent msg[{}].", session.getId(), writeRequest.getMessage());
		super.messageSent(nextFilter, session, writeRequest);
	}
}
