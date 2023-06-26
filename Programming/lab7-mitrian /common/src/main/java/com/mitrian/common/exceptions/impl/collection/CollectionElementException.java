package com.mitrian.common.exceptions.impl.collection;

import com.mitrian.common.exceptions.CollectionException;

/**
 * Exception if collection has some troubles with element
 */
public class CollectionElementException extends CollectionException
{
    public CollectionElementException(String message) {
        super(message);
    }
}
