package com.mitrian.lab.util.exceptions;

public class InappropriateCatStateException extends Exception
{

	private static final String EXCEPTION_PREFIX = "Inappropriate cat state: ";

	public InappropriateCatStateException(String message)
	{
		super(EXCEPTION_PREFIX + message);
	}

}
