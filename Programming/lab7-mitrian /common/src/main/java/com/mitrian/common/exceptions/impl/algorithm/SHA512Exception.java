package com.mitrian.common.exceptions.impl.algorithm;

import com.mitrian.common.exceptions.AlgorithmException;

import java.security.NoSuchAlgorithmException;

public class SHA512Exception extends NoSuchAlgorithmException {
    public SHA512Exception(String message) {
        super(message);
    }
}
