package com.xyang.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-mail.xml")
public class MailSenderTest {
	@Autowired
	private MailSenderService mailSenderService;

	@Test
	public void sendMailTest() throws Exception {
		MailBean mailBean = new MailBean();
		mailBean.setFrom("18610450436@163.com");
		mailBean.setFromName("xyang");
		mailBean.setSubject("你好");
		mailBean.setToEmails(new String[] { "956172390@qq.com","yangxudong@yitong111.com" });
		mailBean.setContext("<a href='www.baidu.com'><font color='red'>fdsfdsf</font></a>");
		mailSenderService.sendMail(mailBean);
	}
}
