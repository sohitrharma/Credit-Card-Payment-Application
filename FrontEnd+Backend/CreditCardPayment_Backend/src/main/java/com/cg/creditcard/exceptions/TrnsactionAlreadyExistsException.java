package com.cg.creditcard.exceptions;

public class TrnsactionAlreadyExistsException  extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TrnsactionAlreadyExistsException(String error) {
		super(error);
	}
	public TrnsactionAlreadyExistsException() {};

}
