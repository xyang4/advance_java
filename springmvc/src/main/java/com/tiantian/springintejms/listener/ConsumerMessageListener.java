package com.tiantian.springintejms.listener;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConverter;

import com.tiantian.springintejms.entity.Email;

public class ConsumerMessageListener implements MessageListener {

	private MessageConverter messageConverter;
	private static Logger logger = LoggerFactory.getLogger(ConsumerMessageListener.class);

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			// 这里我们知道生产者发送的就是一个纯文本消息，所以这里可以直接进行强制转换
			TextMessage textMsg = (TextMessage) message;
			// logger.info("接收到一个【{}】消息。","TextMessage");
			System.out.println("接收到一个纯文本消息。");
			try {
				System.out.println("消息内容是：" + textMsg.getText());
				// 事物处理测试
//				if (1 == 1) throw new RuntimeException("Error");
			} catch (JMSException e) {
				e.printStackTrace();
			}
		} else if (message instanceof MapMessage) {
			MapMessage mapMessage = (MapMessage) message;
			logger.info("接收到一个【{}】消息。", "MapMessage");
			System.out.println(mapMessage.toString());
		} else if (message instanceof ObjectMessage) {
			ObjectMessage objMessage = (ObjectMessage) message;
			logger.info("接收到一个【{}】消息。", "ObjectMessage");
			try {
				/*
				 * Object obj = objMessage.getObject(); Email email = (Email)
				 * obj;
				 */
				Email email = (Email) messageConverter.fromMessage(objMessage);
				System.out.println(email);
			} catch (JMSException e) {
				e.printStackTrace();
			}

		}
	}

	public MessageConverter getMessageConverter() {
		return messageConverter;
	}

	public void setMessageConverter(MessageConverter messageConverter) {
		this.messageConverter = messageConverter;
	}

}
