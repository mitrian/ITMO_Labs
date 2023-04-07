package com.mitrian.lab.common.exceptions.impl.collection;

import com.mitrian.lab.common.exceptions.CollectionException;

/**
 * Exception if there's no such id in elements of collection
 */
public class IdUnavailableException extends CollectionException {

    public IdUnavailableException(String message) {
        super(message);
    }
}
