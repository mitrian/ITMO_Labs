package com.mitrian.lab.common.exceptions.impl.commands;

import com.mitrian.lab.common.exceptions.CommandException;

/**
 * Exception if command is unsupportable
 */
public class CommandNotFoundException extends CommandException {
    public CommandNotFoundException(String message) {
        super(message);
    }
}
