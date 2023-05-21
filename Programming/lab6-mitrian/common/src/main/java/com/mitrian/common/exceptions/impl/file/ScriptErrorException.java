package com.mitrian.common.exceptions.impl.file;

import com.mitrian.common.exceptions.FileException;

public class ScriptErrorException extends FileException
{
    public ScriptErrorException(String message) {
        super(message);
    }
}
