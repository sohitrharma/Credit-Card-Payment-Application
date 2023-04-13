package com.cg.creditcard.exceptions;

public class TransactionNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionNotFoundException(String error) {
		super(error);
	}
	public TransactionNotFoundException() {};

}
