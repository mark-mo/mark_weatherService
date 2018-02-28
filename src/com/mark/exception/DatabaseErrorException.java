package com.mark.exception;

public class DatabaseErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseErrorException(Throwable e) {
		super(e);
	}
}
