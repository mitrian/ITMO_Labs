package com.mitrian.lab.util.exceptions;

public class UnsupportedTypeOfEntityPropertyParameter extends RuntimeException
{

	private static final String EXCEPTION_PREFIX = "Unsupported type of entity property parameter: ";

	public UnsupportedTypeOfEntityPropertyParameter(String message)
	{
		super(EXCEPTION_PREFIX + message);
	}

}
