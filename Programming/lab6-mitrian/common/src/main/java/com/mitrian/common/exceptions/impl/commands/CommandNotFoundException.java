package com.mitrian.common.exceptions.impl.commands;

import com.mitrian.common.exceptions.CommandException;

/**
 * Exception if command is unsupportable
 */
public class CommandNotFoundException extends CommandException
{
    public CommandNotFoundException(String message) {
        super(message);
    }
}
