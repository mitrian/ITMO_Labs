package com.mitrian.lab.common.exceptions.impl.file;

import com.mitrian.lab.common.exceptions.FileException;

public class NoSuchFileException extends FileException {
    public NoSuchFileException(String message) {
        super(message);
    }
}
