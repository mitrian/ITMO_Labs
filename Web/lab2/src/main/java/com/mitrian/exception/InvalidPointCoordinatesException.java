package com.mitrian.exception;

public class InvalidPointCoordinatesException extends ValidationException
{
	public InvalidPointCoordinatesException(String message) {
		super(message);
	}

	public InvalidPointCoordinatesException(String message, Throwable cause) {
		super(message, cause);
	}
}
