package com.perseus.urs.userrestservice.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {
	private String searchProperty;

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

	/**
	 * Instantiates a new missing data exception.
	 *
	 * @param message the message
	 * @param id      the id of entity to be found
	 */
	public BaseException(final String message, Long id) {
		super(message);
		this.searchProperty = String.valueOf(id);
	}

	/**
	 * Instantiates a new missing data exception.
	 *
	 * @param message        the message
	 * @param searchProperty the property of entity to be found
	 */
	public BaseException(final String message, String searchProperty) {
		super(message);
		this.searchProperty = searchProperty;
	}

	public boolean hasValue() {
		return searchProperty != null && !searchProperty.equals("");
	}
}
