package com.xyang.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailSenderService")
public class MailSenderService {
	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;

	/**
	 * 创建MimeMessage
	 * 
	 * @param mailBean
	 * @return
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public MimeMessage createMimeMessage(MailBean mailBean) throws Exception {
		MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true, "UTF-8");
		messageHelper.setFrom(mailBean.getFrom(), mailBean.getFromName());
		messageHelper.setSubject(mailBean.getSubject());
		messageHelper.setTo(mailBean.getToEmails());
		messageHelper.setText(mailBean.getContext(), true); // html: true
		return mimeMessage;
	}

	public void sendMail(MailBean mailBean) throws Exception {
		MimeMessage msg = createMimeMessage(mailBean);
		javaMailSenderImpl.send(msg);
	}
}
