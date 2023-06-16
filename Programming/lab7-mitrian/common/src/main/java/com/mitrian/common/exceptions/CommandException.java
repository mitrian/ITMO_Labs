package com.mitrian.common.exceptions;

/**
 * Abstract command exception
 */
public abstract class CommandException extends Exception{
    public CommandException(String message){
        super(message);
    }
}
