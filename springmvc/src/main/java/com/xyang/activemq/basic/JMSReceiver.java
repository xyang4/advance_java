package com.xyang.activemq.basic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSReceiver {
	
	public static void main(String[] args) throws Exception{
		ConnectionFactory cf = new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = cf.createConnection("root","admin");
		connection.start();
		
		final Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("basic_msg");
		
		MessageConsumer consumer = session.createConsumer(destination);
		int i=100;
		while(i>0){
			TextMessage msg = (TextMessage) consumer.receive();
			session.commit();
			System.out.printf("NO[%d]收到的信息为%s。\n",i--,msg.getText());
		}
	}
}
