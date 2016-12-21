package com.springjms.listener;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;

/**
 * @描述 Spring提供，非标准的jms MessageListener
 * @date 2016年11月5日-下午6:46:31
 * @author IBM
 *
 */
public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<TextMessage> {

	private Destination destination;

	@Override
	public void onMessage(TextMessage message, Session session) throws JMSException {
		System.out.println("收到一条消息");
		System.out.println("消息内容是：" + message.getText());
		MessageProducer producer = session.createProducer(destination);
		Message textMessage = session.createTextMessage("ConsumerSessionAwareMessageListener。。。");
		producer.send(textMessage);
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}

}
