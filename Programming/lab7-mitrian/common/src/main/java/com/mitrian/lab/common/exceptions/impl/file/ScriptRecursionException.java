package com.mitrian.lab.common.exceptions.impl.file;

import com.mitrian.lab.common.exceptions.FileException;

/**
 * Exception if recursion is possible
 */
public class ScriptRecursionException extends FileException {
    public ScriptRecursionException(String message) {
        super(message);
    }
}
