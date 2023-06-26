package com.mitrian.common.exceptions.impl.user;

import com.mitrian.common.exceptions.UserException;

public class UserPasswordLengthException extends UserException {
    public UserPasswordLengthException(String message) {
        super(message);
    }

    public UserPasswordLengthException(String message, Throwable cause) {
        super(message, cause);
    }
}
