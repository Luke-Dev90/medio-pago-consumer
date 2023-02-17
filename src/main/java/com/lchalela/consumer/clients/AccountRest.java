package com.lchalela.consumer.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lchalela.consumer.model.AsyncTransaction;

@FeignClient(name = "account-service")
public interface AccountRest {

	@PostMapping("/v1/async")
	public ResponseEntity<String> postAsyncTransaction(@RequestBody AsyncTransaction transactionDTO);
}
