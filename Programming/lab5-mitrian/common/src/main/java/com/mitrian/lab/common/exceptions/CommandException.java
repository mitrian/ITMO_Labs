package com.mitrian.lab.common.exceptions;

/**
 * Abstract command exception
 */
public abstract class CommandException extends Exception{
    public CommandException(String message){
        super(message);
    }
}
