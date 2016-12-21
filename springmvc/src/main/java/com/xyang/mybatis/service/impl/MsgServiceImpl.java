package com.xyang.mybatis.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xyang.mybatis.dao.MessageDao;
import com.xyang.mybatis.entity.Message;
import com.xyang.mybatis.entity.Page;
import com.xyang.mybatis.service.IMsgService;
public class MsgServiceImpl implements IMsgService{
	@Override
	public List<Message> queryMessageList(String command,String description,Page page) {
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		MessageDao messageDao = new MessageDao();
		int totalNumber = messageDao.count(message);
		page.setTotalNumber(totalNumber);
		Map<String,Object> parameter = new HashMap<String, Object>();
		parameter.put("message", message);
		parameter.put("page", page);
		return messageDao.queryMessageList(parameter);
	}
	
	/**
	 * 根据查询条件分页查询消息列表
	 */
	@Override
	public List<Message> queryMessageListByPage(String command,String description,Page page) {
		Map<String,Object> parameter = new HashMap<String, Object>();
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		parameter.put("message", message);
		parameter.put("page", page);
		MessageDao messageDao = new MessageDao();
		return messageDao.queryMessageListByPage(parameter);
	}
}
