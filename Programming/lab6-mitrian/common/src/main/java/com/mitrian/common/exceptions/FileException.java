package com.mitrian.common.exceptions;

/**
 * Abstract file exception
 */
public abstract class FileException extends Exception {
    public FileException(String message) {
        super(message);
    }
}
