package com.mitrian.common.exceptions.impl.file;

import com.mitrian.common.exceptions.FileException;

/**
 * Exception if file is not writable
 */
public class FileWritableException extends FileException
{
    public FileWritableException(String message){
        super(message);
    }
}
