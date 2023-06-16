package com.mitrian.common.exceptions.impl.file;

import com.mitrian.common.exceptions.FileException;

/**
 * Exception if file is not readable
 */
public class FileReadableException extends FileException {
    public FileReadableException(String message) {
        super(message);
    }
}
