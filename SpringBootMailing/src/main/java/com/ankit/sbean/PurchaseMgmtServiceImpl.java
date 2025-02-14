package com.ankit.sbean;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service("shopping")
public class PurchaseMgmtServiceImpl implements IPurchaseMgmtService{
	
	public PurchaseMgmtServiceImpl() {
		System.out.println("PurchaseMgmtServiceImpl::0-arg constructor");
	}

	@Value("${spring.mail.username}")
	private String fromEmailId;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public String shopping(String[] items, Double[] prices, String[] toEmails) throws Exception {
		System.out.println("PurchaseMgmtServiceImpl.shopping()");
		//calculate the billAmount
		double totalBillAmt = 0.0;
		for(double price : prices) {
			totalBillAmt = totalBillAmt+price;
		}
		String msg1 = "Mr/Mrs/Miss are purchesed "+Arrays.toString(items)+" having prices "+Arrays.toString(prices)+" and the total bill amount is "+totalBillAmt;
		//trigger the mail message
		String mailMessage = sendMail(msg1, toEmails, fromEmailId);
		return msg1+"...."+mailMessage;
	}
	
	private String sendMail(String msg,String[] toEmail,String fromEmailId) throws Exception{
		//create and send MultiplePartMIME message
		MimeMessage message = mailSender.createMimeMessage();	//represent empty email message
		MimeMessageHelper helper = new MimeMessageHelper(message,true); // represent email message allowing the attachment
		//setting the header value
		helper.setSentDate(new Date());
		helper.setFrom(fromEmailId);
		helper.setCc(toEmail);
		helper.setSubject("please open to know it..");
		//set multiple body
		helper.setText(msg); //text part
		helper.addAttachment("coat.jpeg", new ClassPathResource("coat.jpeg"));
		helper.addAttachment("bluthooth.png", new ClassPathResource("bluthooth.png"));
		helper.addAttachment("tshirt.jpg", new ClassPathResource("tshirt.jpg"));
		//send the message
		mailSender.send(message);
		return "email message is sent...";
	}
}
