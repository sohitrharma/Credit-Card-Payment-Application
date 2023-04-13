package com.cg.creditcard.exceptions;

public class InvalidPaymentException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPaymentException(String error) {
		super(error);
	}
	public InvalidPaymentException() {}
}
