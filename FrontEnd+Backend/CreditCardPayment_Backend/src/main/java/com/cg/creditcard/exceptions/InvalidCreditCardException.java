package com.cg.creditcard.exceptions;

public class InvalidCreditCardException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCreditCardException(String error) {
		super(error);
	}

	public InvalidCreditCardException() {
	}
}
