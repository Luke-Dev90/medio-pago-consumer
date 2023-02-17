package com.lchalela.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.lchalela.consumer.clients.AccountRest;

@EnableScheduling
@SpringBootApplication
@EnableFeignClients(clients = {AccountRest.class})
public class LchalelaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LchalelaConsumerApplication.class, args);
	}

}
