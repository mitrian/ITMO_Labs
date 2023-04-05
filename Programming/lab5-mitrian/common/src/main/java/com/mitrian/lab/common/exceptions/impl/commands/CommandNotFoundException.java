package com.mitrian.lab.common.exceptions.impl.commands;

import com.mitrian.lab.common.exceptions.CommandException;

public class CommandNotFoundException extends CommandException {
    public CommandNotFoundException(String message) {
        super(message);
    }
}
