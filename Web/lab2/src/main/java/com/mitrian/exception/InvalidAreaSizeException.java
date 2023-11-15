package com.mitrian.exception;

public class InvalidAreaSizeException extends ValidationException {
	public InvalidAreaSizeException(String message) {
		super(message);
	}

	public InvalidAreaSizeException(String message, Throwable cause) {
		super(message, cause);
	}
}
