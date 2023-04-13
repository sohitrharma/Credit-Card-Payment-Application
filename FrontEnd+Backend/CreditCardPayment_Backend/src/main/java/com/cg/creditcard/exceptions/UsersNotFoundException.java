package com.cg.creditcard.exceptions;

public class UsersNotFoundException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	public UsersNotFoundException() {
	// TODO Auto-generated constructor stub
	}
	public UsersNotFoundException(String message) {	
		super(message);
	}
	

}