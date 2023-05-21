package com.mitrian.common.exceptions.impl.collection;

import com.mitrian.common.exceptions.CollectionException;

/**
 * Exception if there's no such id in elements of collection
 */
public class IdUnavailableException extends CollectionException
{

    public IdUnavailableException(String message) {
        super(message);
    }
}
