package com.perseus.urs.userrestservice.exception;

import lombok.Getter;

@Getter
public class BadDataException extends BaseException {

	public BadDataException(final String message) {
		super(message);
	}

	/**
	 * Instantiates a new missing data exception.
	 *
	 * @param message        the message
	 * @param searchProperty the property to search enitity with
	 */
	public BadDataException(String message, String searchProperty) {
		super(message, searchProperty);
	}

	/**
	 * Instantiates a new missing data exception.
	 *
	 * @param message        the message
	 * @param searchProperty the property to search enitity with
	 */
	public BadDataException(String message, Long searchProperty) {
		super(message, searchProperty);
	}
}
