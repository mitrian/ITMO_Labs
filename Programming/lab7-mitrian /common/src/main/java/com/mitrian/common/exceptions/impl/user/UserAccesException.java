package com.mitrian.common.exceptions.impl.user;

import com.mitrian.common.exceptions.UserException;

public class UserAccesException extends UserException {
    public UserAccesException(String message) {
        super(message);
    }

    public UserAccesException(String message, Throwable cause) {
        super(message, cause);
    }
}
