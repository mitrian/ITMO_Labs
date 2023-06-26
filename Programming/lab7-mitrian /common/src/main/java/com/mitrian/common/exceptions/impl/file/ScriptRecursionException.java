package com.mitrian.common.exceptions.impl.file;

import com.mitrian.common.exceptions.FileException;

/**
 * Exception if recursion is possible
 */
public class ScriptRecursionException extends FileException
{
    public ScriptRecursionException(String message) {
        super(message);
    }
}
