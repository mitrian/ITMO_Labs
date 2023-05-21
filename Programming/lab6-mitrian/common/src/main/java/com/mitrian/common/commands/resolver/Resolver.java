package com.mitrian.common.commands.resolver;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.resolver.exception.ScriptResolvingException;
import com.mitrian.common.exceptions.impl.commands.CommandNotFoundException;

import java.io.BufferedReader;
import java.util.List;

/**
 * Class for retrieving command object and arguments
 */
public interface Resolver {

    /**
     * Resolving command from console input
     * @param userInput param for user input line
     * @return command object
     * @throws CommandNotFoundException exception for unsupported command and ctrl+D
     */
    default AbstractCommand resolve(String userInput) throws CommandNotFoundException
    {
        throw new UnsupportedOperationException("");
    }

    /**
     * Resolving command from file
     * @param parsedScript parsed script lines
     * @return List of command objects
     * @throws ScriptResolvingException unsupportable command, absence of file and wrong Worker field
     */
    default List<AbstractCommand> resolve(BufferedReader reader, List<String> parsedScript) throws ScriptResolvingException
    {
        throw new UnsupportedOperationException("");
    }
}
