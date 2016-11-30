package com.xyang.mybatis.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyang.mybatis.entity.Message;
import com.xyang.mybatis.entity.Page;
import com.xyang.mybatis.mapper.MsgMapper;
import com.xyang.mybatis.service.IMsgService;
@Service
public class MsgServiceImpl2 implements IMsgService {
	@Autowired
	private MsgMapper msgMapper;

	@Override
	public List<Message> queryMessageList(String command, String description,
			Page page) {
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		int totalNumber = msgMapper.count(message);
		page.setTotalNumber(totalNumber);
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("message", message);
		parameter.put("page", page);
		return msgMapper.queryMessageList(parameter);
	}

	/**
	 * 根据查询条件分页查询消息列表
	 */
	@Override
	public List<Message> queryMessageListByPage(String command,
			String description, Page page) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		parameter.put("message", message);
		parameter.put("page", page);
		return msgMapper.queryMessageListByPage(parameter);
	}
}
