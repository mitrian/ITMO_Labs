package com.mitrian.common.exceptions;

public abstract class UserException extends Exception {
    public UserException(String message){super(message);}
    public UserException(String message, Throwable cause){super(message, cause);}
}
