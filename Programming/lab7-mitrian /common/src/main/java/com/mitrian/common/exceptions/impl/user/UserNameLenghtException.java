package com.mitrian.common.exceptions.impl.user;

import com.mitrian.common.exceptions.UserException;

public class UserNameLenghtException extends UserException {
    public UserNameLenghtException(String message) {
        super(message);
    }

    public UserNameLenghtException(String message, Throwable cause) {
        super(message, cause);
    }
}
