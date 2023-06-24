package com.mitrian.common.exceptions.impl.user;

import com.mitrian.common.exceptions.UserException;

public class UserExistenceException extends UserException {
    public UserExistenceException(String message) {
        super(message);
    }

    public UserExistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
