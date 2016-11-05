package com.xyang.activemq.producer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xyang.activemq.producer.entity.MailParam;

public class MQProducerTest {
	private static final Log log = LogFactory.getLog(MQProducerTest.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath:spring/spring-mq-producer.xml");
			context.start();

			final MQProducer mqProducer = (MQProducer) context.getBean("mqProducer");
			for (int i = 0; i < 1000; i++) {
				// 邮件发送
//				MailParam mail = new MailParam();
//				Thread t = Thread.currentThread();
//				mail.setTo("user" +i);
//				mail.setSubject(t.getName());
//				mail.setContent("邮件");
				mqProducer.sendMessage("msg "+i);
			}

			 context.stop();
		} catch (Exception e) {
			log.error("==>MQ context start error:", e);
			System.exit(0);
		} finally {
			log.info("===>System.exit");
			System.exit(0);
		}
	}
}
