package com.cg.creditcard.exceptions;

public class UserNotFoundException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	public UserNotFoundException() {
	// TODO Auto-generated constructor stub
	}
	public UserNotFoundException(String message) {	
		super(message);
	}
	

}
