package com.mitrian.common.exceptions;

/**
 * Exception with command arguments are wrong
 */
public class IncorrectCommandArgumentException extends Exception{

    public IncorrectCommandArgumentException(String message){super(message);}
}
