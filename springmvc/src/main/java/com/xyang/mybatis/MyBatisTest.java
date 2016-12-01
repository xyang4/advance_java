package com.xyang.mybatis;

import java.util.List;

import com.xyang.mybatis.entity.Message;
import com.xyang.mybatis.entity.Page;
import com.xyang.mybatis.service.impl.MsgServiceImpl;

public class MyBatisTest {
	public static void main(String[] args) {
		List<Message> messages = new MsgServiceImpl().queryMessageList("查看",
				null, new Page(1));
		for (Message msg : messages) {
			System.out.println(msg);
		}
	}
}
