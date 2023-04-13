package com.cg.creditcard.exceptions;

public class CreditCardNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreditCardNotFoundException(String error) {
		super(error);
	}

	public CreditCardNotFoundException() {
	};

}
