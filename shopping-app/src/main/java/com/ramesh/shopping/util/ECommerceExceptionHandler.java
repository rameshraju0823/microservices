package com.ramesh.shopping.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ramesh.shopping.exceptions.AuthenticationException;
import com.ramesh.shopping.exceptions.DuplicateUserException;
import com.ramesh.shopping.exceptions.ErrorException;
import com.ramesh.shopping.exceptions.NoSuchUserException;
import com.ramesh.shopping.exceptions.ProductExistsException;
import com.ramesh.shopping.exceptions.ProductNotExistsException;
import com.ramesh.shopping.exceptions.TransactionFailedException;
import com.ramesh.shopping.exceptions.UnHandledCartException;

import feign.FeignException;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ECommerceExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(DuplicateUserException.class)
	protected ResponseEntity<ErrorException> handleWhileDuplicateEmail(DuplicateUserException e){
		
		return new ResponseEntity<>(ErrorException
				.builder()
				.errorCode(500)
				.errorMessage(e.getMessage())
				.build(), HttpStatus.ALREADY_REPORTED);
	}
	
	@ExceptionHandler(NoSuchUserException.class)
	protected ResponseEntity<ErrorException> handleWhileUserNotFound(NoSuchUserException e){
		
		return new ResponseEntity<>(ErrorException
				.builder()
				.errorCode(500)
				.errorMessage(e.getMessage())
				.build(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TransactionFailedException.class)
	protected ResponseEntity<ErrorException> handleWhenTransactionFailed(RuntimeException e){
		return new ResponseEntity<>(ErrorException
				.builder()
				.errorCode(500)
				.errorMessage(e.getMessage())
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(UnHandledCartException.class)
	protected ResponseEntity<ErrorException> handleWhenUnhandledCart(RuntimeException e){
		return new ResponseEntity<>(ErrorException
				.builder()
				.errorCode(500)
				.errorMessage(e.getMessage())
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(ProductExistsException.class)
	protected ResponseEntity<ErrorException> handleWhenProductExistsInCart(RuntimeException e){
		return new ResponseEntity<>(ErrorException
				.builder()
				.errorCode(500)
				.errorMessage(e.getMessage())
				.build(), HttpStatus.ALREADY_REPORTED);
	}
	@ExceptionHandler(ProductNotExistsException.class)
	protected ResponseEntity<ErrorException> handleWhenProductNotExistsInCart(RuntimeException e){
		return new ResponseEntity<>(ErrorException
				.builder()
				.errorCode(500)
				.errorMessage(e.getMessage())
				.build(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(AuthenticationException.class)
	protected ResponseEntity<ErrorException> handleWhenPasswordIsWrong(RuntimeException e){
		return new ResponseEntity<>(ErrorException
				.builder()
				.errorCode(500)
				.errorMessage(e.getMessage())
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(java.lang.IndexOutOfBoundsException.class)
	protected ResponseEntity<ErrorException> handleWhenNoProductsInCart(RuntimeException e){
		return new ResponseEntity<>(ErrorException
				.builder()
				.errorCode(500)
				.errorMessage("Please add items in cart to make payment")
				.build(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(FeignException.class)
	protected ResponseEntity<String> handleWhenErrorInFundTransfer(FeignException e){
		return new ResponseEntity<>(e.contentUTF8(), HttpStatus.INTERNAL_SERVER_ERROR);
	}	

}
