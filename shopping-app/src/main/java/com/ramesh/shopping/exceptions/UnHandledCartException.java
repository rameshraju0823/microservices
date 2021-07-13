package com.ramesh.shopping.exceptions;

public class UnHandledCartException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public UnHandledCartException(String message) {
        super(message);
    }

}
