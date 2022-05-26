package com.perseus.urs.userrestservice.exception;

public class NotFoundException extends BaseException {

	/**
	 * Instantiates a new missing data exception.
	 */
	public NotFoundException() {
		super();
	}

	/**
	 * Instantiates a new missing data exception.
	 *
	 * @param message the message
	 */
	public NotFoundException(final String message) {
		super(message);
	}
}
