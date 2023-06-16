package com.mitrian.common.exceptions;

/**
 * Exception if field isn't validated
 */
public class IncorrectFieldException extends Exception {

    public IncorrectFieldException(String message){super(message);}
}