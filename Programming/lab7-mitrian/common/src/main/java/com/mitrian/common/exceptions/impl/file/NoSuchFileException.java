package com.mitrian.common.exceptions.impl.file;

import com.mitrian.common.exceptions.FileException;

/**
 * Exception if file with such name doesn't exist
 */
public class NoSuchFileException extends FileException
{
    public NoSuchFileException(String message) {
        super(message);
    }
}
