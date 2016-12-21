package com.xyang.mybatis.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.xyang.mybatis.db.DBAccess;
import com.xyang.mybatis.entity.Message;
import com.xyang.mybatis.mapper.MsgMapper;

/**
 * 和message表相关的数据库操作
 */
public class MessageDao {
	private static DBAccess dbAccess = new DBAccess();

	/**
	 * 根据查询条件查询消息列表
	 */
	public List<Message> queryMessageList(Map<String, Object> parameter) {

		List<Message> messageList = new ArrayList<Message>();
		SqlSession sqlSession = null;
		try {
			// 1. 通过sqlSession执行SQL语句
			sqlSession = dbAccess.getSqlSession();
			// 2.通过SQLSession获取动态代理的mapperProxy
			MsgMapper imessage = sqlSession.getMapper(MsgMapper.class);
			messageList = imessage.queryMessageList(parameter);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return messageList;
	}

	/**
	 * 根据查询条件分页查询消息列表
	 */
	public List<Message> queryMessageListByPage(Map<String, Object> parameter) {
		List<Message> messageList = new ArrayList<Message>();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// 通过sqlSession执行SQL语句
			MsgMapper imessage = sqlSession.getMapper(MsgMapper.class);
			messageList = imessage.queryMessageListByPage(parameter);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return messageList;
	}

	public int count(Message message) {
		SqlSession sqlSession = null;
		int result = 0;
		try {
			sqlSession = dbAccess.getSqlSession();
			// 通过sqlSession执行SQL语句
			MsgMapper imessage = sqlSession.getMapper(MsgMapper.class);
			result = imessage.count(message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}
}
