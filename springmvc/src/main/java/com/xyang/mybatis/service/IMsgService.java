package com.xyang.mybatis.service;

import java.util.List;

import com.xyang.mybatis.entity.Message;
import com.xyang.mybatis.entity.Page;

public interface IMsgService {

	List<Message> queryMessageList(String command, String description, Page page);

	List<Message> queryMessageListByPage(String command, String description, Page page);
}
