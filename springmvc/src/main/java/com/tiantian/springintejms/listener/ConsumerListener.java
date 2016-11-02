package com.tiantian.springintejms.listener;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import com.tiantian.springintejms.entity.Email;

public class ConsumerListener {

	public void handleMessage(String message) {
		System.out.println("ConsumerListenerͨ��handleMessage���յ�һ�����ı���Ϣ����Ϣ�����ǣ�" + message);
	}
	
	/*public void receiveMessage(String message) {
		System.out.println("ConsumerListenerͨ��receiveMessage���յ�һ�����ı���Ϣ����Ϣ�����ǣ�" + message);
	}*/
	
	/**
	 * �����������Ƿ�nullʱMessageListenerAdapter���Զ��ѷ���ֵ��װ��һ��Message��Ȼ����лظ�
	 * @param message
	 * @return
	 */
	public String receiveMessage(String message) {
		System.out.println("ConsumerListenerͨ��receiveMessage���յ�һ�����ı���Ϣ����Ϣ�����ǣ�" + message);
		return "����ConsumerListener�����receiveMessage�����ķ���ֵ��";
	}
	
	
	public void receiveMessage(Email email) {
		System.out.println("���յ�һ������Email��ObjectMessage��");
		System.out.println(email);
	}
	
	public void receiveMessage(ObjectMessage message) throws JMSException {
		System.out.println(message.getObject());
	}
	
}
