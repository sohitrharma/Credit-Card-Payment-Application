package com.cg.creditcard.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistsException() {
	// TODO Auto-generated constructor stub
	}
	public UserAlreadyExistsException(String message) {	
		super(message);
	}
	

}