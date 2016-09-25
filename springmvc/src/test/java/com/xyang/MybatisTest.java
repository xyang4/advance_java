package com.xyang;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import com.xyang.mybatis.db.DBAccess;
import com.xyang.mybatis.entity.Message;
import com.xyang.mybatis.page.Page;
import com.xyang.mybatis.service.QueryService;

public class MybatisTest {
	private QueryService service;

	@Before
	public void init() {
		service = new QueryService();
	}

	@Test
	public void queryTest() {
		service.queryMessageList("1", "a", new Page(1, 3));
	}

	@Test
	public static void dbAccessTest() throws IOException {
		DBAccess ac = new DBAccess();
		SqlSession session = ac.getSqlSession();
		System.out.println(session.toString());
	}
	public static void main(String[] args) {
		List<Message> result = new QueryService().queryMessageList("1", null, new Page(1, 3));
		System.out.println(result.size());
	}
}
