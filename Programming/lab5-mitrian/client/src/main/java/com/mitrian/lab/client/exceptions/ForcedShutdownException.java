package com.mitrian.lab.client.exceptions;

public class ForcedShutdownException extends Exception{

    private static final String EXCEPTION_PREFIX = "Не нажимайте ctrl+D. ";

    public ForcedShutdownException(String message){super(message+". "+ EXCEPTION_PREFIX);}
}
