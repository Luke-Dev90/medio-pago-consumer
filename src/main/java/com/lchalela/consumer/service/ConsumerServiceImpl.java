package com.lchalela.consumer.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lchalela.consumer.clients.AccountRest;
import com.lchalela.consumer.model.AsyncTransaction;
import com.lchalela.consumer.repositoy.AsyncTransactionRepository;

@Service
public class ConsumerServiceImpl implements ConsumerService{
	
	private AsyncTransactionRepository asyncTransactionRepository;
	private AccountRest accountRest;
	private Logger logger = LoggerFactory.getLogger(ConsumerServiceImpl.class);
	
	@Autowired
	public ConsumerServiceImpl(AsyncTransactionRepository asyncTransactionRepository,AccountRest accountRest) {
		this.asyncTransactionRepository = asyncTransactionRepository;
		this.accountRest = accountRest;
	}

	@Override
	public List<AsyncTransaction> listAsyncTransaction() {
		List<AsyncTransaction> transactions = this.asyncTransactionRepository
				.findByDateAcreditationBetween(getDate(false), getDate(true));
		return transactions;
	}
	
	@Scheduled(fixedRate = 60000)
	public void impactAysncTransactions() {
		
		logger.info("getting list transactions");
		
		List<AsyncTransaction> transactions = this.listAsyncTransaction();
		
		if(!transactions.isEmpty()) {
			
			logger.info("start async transactions");
			for(AsyncTransaction t : transactions) {
				
				logger.info("sent transaction to MS: account");
				System.out.println( t );
				this.accountRest.postAsyncTransaction(t);
				
				logger.info("Empty DB");
				this.asyncTransactionRepository.delete(t);
			}
			
		}else {
			logger.info("Not found transactions async");
		}
		
	}
	
	public static LocalDateTime getDate(boolean endDay) {
		LocalDate c = LocalDate.now();
		
		if(endDay) {
			return LocalDateTime.of(c.getYear(), c.getMonthValue(), c.getDayOfMonth(), 18, 0);
		}
		return LocalDateTime.of(c.getYear(), c.getMonthValue(), c.getDayOfMonth(),8 , 0);
	}
	
	
}
