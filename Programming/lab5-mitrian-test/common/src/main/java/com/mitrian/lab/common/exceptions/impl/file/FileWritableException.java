package com.mitrian.lab.common.exceptions.impl.file;

import com.mitrian.lab.common.exceptions.FileException;

/**
 * Exception if file is not writable
 */
public class FileWritableException extends FileException {
    public FileWritableException(String message){
        super(message);
    }
}
