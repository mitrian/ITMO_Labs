package com.mitrian.lab.common.exceptions.impl.collection;

import com.mitrian.lab.common.exceptions.CollectionException;

/**
 * Exception if collection has some troubles with element
 */
public class CollectionElementException extends CollectionException {
    public CollectionElementException(String message) {
        super(message);
    }
}
