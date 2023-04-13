package com.cg.creditcard.exceptions;

public class StatementNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public StatementNotFoundException(String message)
	{
		super(message);
	}

}
