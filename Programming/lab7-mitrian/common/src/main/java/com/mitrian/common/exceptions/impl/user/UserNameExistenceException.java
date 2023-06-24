package com.mitrian.common.exceptions.impl.user;

import com.mitrian.common.exceptions.UserException;

public class UserNameExistenceException extends UserException {
    public UserNameExistenceException(String message) {
        super(message);
    }

    public UserNameExistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
