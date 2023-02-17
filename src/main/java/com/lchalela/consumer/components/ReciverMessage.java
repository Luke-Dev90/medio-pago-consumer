package com.lchalela.consumer.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lchalela.consumer.model.AsyncTransaction;
import com.lchalela.consumer.repositoy.AsyncTransactionRepository;

@Component
public class ReciverMessage {
	
	private Logger logger = LoggerFactory.getLogger(ReciverMessage.class);
	private AsyncTransactionRepository asyncTransactionRepository;
	
	@Autowired
	public ReciverMessage(AsyncTransactionRepository asyncTransactionRepository) {
		this.asyncTransactionRepository = asyncTransactionRepository;
	}
	
	@Transactional
	@JmsListener(destination = "transaction")
	public void recivedMessage(String message) {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		
		try {
			logger.info("Message string parse to Entity");
			AsyncTransaction transaction = mapper.readValue(message, AsyncTransaction.class);
			
			logger.info("Persist entity");
			this.asyncTransactionRepository.save(transaction);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
		}
		
	}
	
}
