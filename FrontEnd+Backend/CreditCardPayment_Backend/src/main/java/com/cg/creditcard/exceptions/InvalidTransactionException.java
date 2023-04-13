package com.cg.creditcard.exceptions;

public class InvalidTransactionException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidTransactionException(String error) {
		super(error);
	}
	public InvalidTransactionException() {}
}
