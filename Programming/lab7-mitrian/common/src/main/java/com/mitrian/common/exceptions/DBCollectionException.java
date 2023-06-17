package com.mitrian.common.exceptions;

public class DBCollectionException extends Exception{
    public DBCollectionException(String message){
        super(message);
    }

    public DBCollectionException(String message, Throwable cause){
        super(message, cause);
    }
}
