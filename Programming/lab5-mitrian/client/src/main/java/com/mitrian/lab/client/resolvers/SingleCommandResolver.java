package com.mitrian.lab.client.resolvers;


import com.mitrian.lab.client.ui.console.WorkerConsoleReader;
import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.client.CommandFactory;
import com.mitrian.lab.common.commands.cmdclasses.ExecuteScriptCommand;
import com.mitrian.lab.common.commands.resolvers.Resolver;
import com.mitrian.lab.common.commands.utils.CommandSource;
import com.mitrian.lab.common.exceptions.ForcedShutdownException;
import com.mitrian.lab.common.exceptions.impl.commands.CommandNotFoundException;
import com.mitrian.lab.common.utils.ConsolePriner;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class for resolving commands from console
 */
public class SingleCommandResolver implements Resolver {
    /**
     * Current Command Factory for initialize command object
     */
    private final CommandFactory commandFactory;

    /**
     * Сonstructor for initialize fields
     * @param commandFactory param for initialize commandFactory field
     */
    public SingleCommandResolver(CommandFactory commandFactory){
        this.commandFactory = commandFactory;
    }

    /**
     * Resolve commands from console
     * @param userInput line of user input
     * @return Command object for executing
     * @throws CommandNotFoundException unsupportable command
     * @throws ForcedShutdownException ctrl+d exception
     */
    @Override
    public AbstractCommand resolve(String userInput) throws CommandNotFoundException, ForcedShutdownException {

        String[] localLine = userInput.trim().split(" ");
        List<String> args = Arrays.asList(
                Arrays.copyOfRange(localLine, 1, localLine.length)
        );
        AbstractCommand localCommand = commandFactory.newCommand(localLine[0], CommandSource.CONSOLE, args);
        if (localCommand.getInputElement()){
            WorkerConsoleReader workerConsoleReader = new WorkerConsoleReader(new Scanner(System.in), new ConsolePriner());
            localCommand.setAdditionalArg(workerConsoleReader.createWorkerObject());
        }
        if (localCommand instanceof ExecuteScriptCommand){
            localCommand.setResolver(new FileCommandResolver(commandFactory));
        }
        return localCommand;
    }
}

