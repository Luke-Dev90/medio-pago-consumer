package com.lchalela.consumer.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ReciverEmails {
	private Logger logger = LoggerFactory.getLogger(ReciverEmails.class);
	
	@JmsListener(destination = "emails")
	public void recivedMessageEmail(String message) {
		logger.info(message);
	}
}
