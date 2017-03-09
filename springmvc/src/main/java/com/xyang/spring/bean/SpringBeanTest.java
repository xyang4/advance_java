package com.xyang.spring.bean;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_beans.xml")
public class SpringBeanTest {
	@Resource(name="person")
	private Person person;
	@Test
	public void bean_test() {
		System.out.println(person);
	}
}
