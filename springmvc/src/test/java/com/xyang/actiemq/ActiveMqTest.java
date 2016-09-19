package com.xyang.actiemq;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.xyang.UnitTestBase;

@RunWith(BlockJUnit4ClassRunner.class)
public class ActiveMqTest extends UnitTestBase {
	public ActiveMqTest() {
		super("classpath*:spring-jms.xml");
	}
	// TODO
}
