package com.cg.creditcard.exceptions;

public class PaymentNotFoundException  extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaymentNotFoundException(String error) {
		super(error);
	}
	public PaymentNotFoundException() {};

}
