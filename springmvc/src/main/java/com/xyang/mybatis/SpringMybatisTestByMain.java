package com.xyang.mybatis;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xyang.mybatis.entity.Message;
import com.xyang.mybatis.entity.Page;
import com.xyang.mybatis.service.IMsgService;

public class SpringMybatisTestByMain {
	private static IMsgService msgService = null;
	public static void main(String[] args) {
		String paths[] = {"applicationContext.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(paths);
		msgService = ac.getBean(IMsgService.class);
		List<Message> messages = msgService.queryMessageList("查看", null,new Page(1));
		for (Message msg : messages) {
			System.out.println(msg);
		}
	}
}
