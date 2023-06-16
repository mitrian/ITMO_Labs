package com.mitrian.common.exceptions.impl.collection;

import com.mitrian.common.exceptions.CollectionException;


/**
 * Exception if collection is already empty
 */
public class CollectionEmptyException extends CollectionException
{

    public CollectionEmptyException(String message) {
        super(message);
    }
}
