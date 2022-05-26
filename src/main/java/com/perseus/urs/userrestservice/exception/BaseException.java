package com.perseus.urs.userrestservice.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {
	/**
	 * Instantiates a new missing data exception.
	 */
	public BaseException() {
		super();
	}

	/**
	 * Instantiates a new missing data exception.
	 *
	 * @param message the message
	 */
	public BaseException(final String message) {
		super(message);
	}
}
