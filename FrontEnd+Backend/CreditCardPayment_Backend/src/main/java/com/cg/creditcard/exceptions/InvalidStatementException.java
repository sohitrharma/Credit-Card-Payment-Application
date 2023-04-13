package com.cg.creditcard.exceptions;

public class InvalidStatementException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidStatementException(String error) {
		super(error);
	}
	public InvalidStatementException() {}

}
