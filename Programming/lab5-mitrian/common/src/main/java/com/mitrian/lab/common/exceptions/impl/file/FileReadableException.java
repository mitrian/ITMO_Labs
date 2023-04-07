package com.mitrian.lab.common.exceptions.impl.file;

import com.mitrian.lab.common.exceptions.FileException;

/**
 * Exception if file is not readable
 */
public class FileReadableException extends FileException {
    public FileReadableException(String message) {
        super(message);
    }
}
