package com.xyang.mybatis;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xyang.mybatis.entity.Message;
import com.xyang.mybatis.entity.Page;
import com.xyang.mybatis.service.IMsgService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringMybatisTestByAno {
	@Autowired
	private IMsgService msgService;

	@Test
	public void query() {
		List<Message> messages = msgService.queryMessageList("查看", null,
				new Page(1));
		for (Message msg : messages) {
			System.out.println(msg);
		}
	}
}
