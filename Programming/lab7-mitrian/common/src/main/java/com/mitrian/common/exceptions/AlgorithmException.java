package com.mitrian.common.exceptions;

import java.security.NoSuchAlgorithmException;

public abstract class AlgorithmException extends NoSuchAlgorithmException {
    public AlgorithmException(String message){super(message);}
}
