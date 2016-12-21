package com.xyang.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.xyang.mybatis.entity.Message;

public interface MsgMapper {
	public List<Message> queryMessageList(Map<String, Object> message);
	/**
	 * 根据查询条件分页查询消息列表
	 */
	public List<Message> queryMessageListByPage(Map<String, Object> message);
	public int count(Message message);
}
