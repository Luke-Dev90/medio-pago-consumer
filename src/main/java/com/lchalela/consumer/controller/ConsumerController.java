package com.lchalela.consumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lchalela.consumer.model.AsyncTransaction;
import com.lchalela.consumer.service.ConsumerServiceImpl;

@RestController
@RequestMapping("/v1")
public class ConsumerController {
	
	
	@Autowired
	private ConsumerServiceImpl consumerServiceImpl;
	
	
	@GetMapping("/transaction-day")
	public ResponseEntity<?> getAllTransactionOfDay(){
		List<AsyncTransaction> list = this.consumerServiceImpl.listAsyncTransaction();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
}
