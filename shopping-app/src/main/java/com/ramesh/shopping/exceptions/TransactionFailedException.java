package com.ramesh.shopping.exceptions;

public class TransactionFailedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TransactionFailedException(String message) {
        super(message);
    }
}
