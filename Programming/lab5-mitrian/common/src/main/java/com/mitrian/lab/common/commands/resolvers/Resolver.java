package com.mitrian.lab.common.commands.resolvers;

import com.mitrian.lab.common.commands.AbstractCommand;

import java.io.File;
import java.util.List;

//TODO: check id in collection
/**
 * Class for retrieving command object and arguments
 */
public interface Resolver {

    /**
     * Resolving command from console input
     * @param userInput param for user input line
     * @return command object
     * @throws Exception exception for unsupported command and ctrl+D
     */
    default AbstractCommand resolve(String userInput) throws Exception {
        throw new UnsupportedOperationException("");
        // TODO: implement throwing an exception: trying to use default method
    }

    /**
     * Resolving command from file
     * @param file param for file
     * @return List of command objects
     * @throws Exception unsupportable command, absence of file and wrong Worker field
     */
    default List<AbstractCommand> resolve(File file) throws Exception {
        throw new UnsupportedOperationException("");
        // TODO: implement throwing an exception: trying to use default method
    }
}
