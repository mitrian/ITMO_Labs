package com.mitrian.lab.common.exceptions.impl.file;

import com.mitrian.lab.common.exceptions.FileException;

public class ScriptErrorException extends FileException {
    public ScriptErrorException(String message) {
        super(message);
    }
}
