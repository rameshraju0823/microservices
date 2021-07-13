package com.ramesh.shopping.exceptions;

public class NoSuchUserException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoSuchUserException(String message) {
		super(message);
	}
}
