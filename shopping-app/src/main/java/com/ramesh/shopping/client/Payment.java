package com.ramesh.shopping.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url="http://localhost:8082/payment", name = "payment-app")
public interface Payment {

	@PostMapping("/{fromAccountId}/{toAccountId}/{amount}")
	public ResponseEntity<String> payment(@PathVariable("fromAccountId") long fromAccountId,
											   @PathVariable("toAccountId") long toAccountId,
											   @PathVariable("amount") double amount);

	
}
