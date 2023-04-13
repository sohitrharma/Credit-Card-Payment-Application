package com.cg.creditcard.exceptions;

public class PaymentAlreadyExistsException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaymentAlreadyExistsException(String error) {
		super(error);
	}
	public PaymentAlreadyExistsException() {}
}
