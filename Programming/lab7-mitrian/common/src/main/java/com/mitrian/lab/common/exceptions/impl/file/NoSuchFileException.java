package com.mitrian.lab.common.exceptions.impl.file;

import com.mitrian.lab.common.exceptions.FileException;

/**
 * Exception if file with such name doesn't exist
 */
public class NoSuchFileException extends FileException {
    public NoSuchFileException(String message) {
        super(message);
    }
}
