package com.xyang.activemq.basic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSSend {
	public static void main(String[] args) throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection =connectionFactory.createConnection("root", "admin");
		connection.start();
		Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("basic_msg");
		MessageProducer msgProducer = session.createProducer(destination);
		
		//发送消息
		for (int i = 0; i < 3; i++) {
			TextMessage tMsg = session.createTextMessage("message--" + i);
			Thread.sleep(1000);
			msgProducer.send(tMsg);
		}
		
		session.commit();
		session.close();
		connection.close();
	}
}
