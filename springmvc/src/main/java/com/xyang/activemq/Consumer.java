package com.xyang.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

/**
 * 消息消费者
 * 
 * @author zhaiyz
 */
public class Consumer implements MessageListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);
	
	private JmsTemplate jmsTemplate;
	
	/**
	 * @return the jmsTemplate
	 */
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	/**
	 * @param jmsTemplate the jmsTemplate to set
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				final String request = textMessage.getText();
				LOGGER.info("接收到的信息: "+request+",name:"+message.getStringProperty("Name"));
			} catch (JMSException e) {
				LOGGER.error("接收信息出错", e);
			}
		}
	}
}
