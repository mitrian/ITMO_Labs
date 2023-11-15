package com.mitrian.exception;

public class NullValidationTargetException extends ValidationException
{
	public NullValidationTargetException(String message) {
		super(message);
	}

	public NullValidationTargetException(String message, Throwable cause) {
		super(message, cause);
	}
}
