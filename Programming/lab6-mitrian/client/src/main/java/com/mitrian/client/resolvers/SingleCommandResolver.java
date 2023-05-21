//package com.mitrian.client.resolvers;
//
//
//import com.mitrian.client.ui.console.WorkerConsoleReader;
//import com.mitrian.common.commands.AbstractCommand;
//import com.mitrian.common.commands.factory.CommandFactory;
//import com.mitrian.common.commands.cmdclass.ExecuteScriptCommand;
//import com.mitrian.common.commands.cmdclass.UpdateCommand;
//import com.mitrian.common.commands.resolver.Resolver;
//import com.mitrian.common.commands.util.parser.ArgumentParser;
//import com.mitrian.common.elements.initializer.IdCollection;
//import com.mitrian.common.exceptions.ForcedShutdownException;
//import com.mitrian.common.exceptions.IncorrectCommandArgumentException;
//import com.mitrian.common.exceptions.IncorrectFieldException;
//import com.mitrian.common.exceptions.impl.collection.IdUnavailableException;
//import com.mitrian.common.exceptions.impl.commands.CommandNotFoundException;
//import com.mitrian.common.exceptions.impl.file.ScriptRecursionException;
//import com.mitrian.client.util.ConsolePrinter;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Scanner;
//
///**
// * Class for resolving commands from console
// */
//public class SingleCommandResolver implements Resolver {
//
//    /** Current Command Factory for initialize command object */
//    private final CommandFactory commandFactory;
//
//
//    /**
//     * Constructor for initialize fields
//     * @param commandFactory param for initialize commandFactory field
//     */
//    public SingleCommandResolver(CommandFactory commandFactory){
//        this.commandFactory = commandFactory;
//    }
//
//    /**
//     * Resolve commands from console
//     * @param userInput line of user input
//     * @return Command object for executing
//     * @throws CommandNotFoundException unsupportable command
//     * @throws ForcedShutdownException ctrl+d exception
//     */
//    @Override
//    public AbstractCommand resolve(String userInput) throws CommandNotFoundException, ForcedShutdownException, IncorrectCommandArgumentException, ScriptRecursionException, IdUnavailableException, IncorrectFieldException {
//
//        String[] localLine = userInput.trim().split(" ");
//        List<String> args = Arrays.asList(
//                Arrays.copyOfRange(localLine, 1, localLine.length)
//        );
//        AbstractCommand localCommand = commandFactory.newCommand(localLine[0], args);
//        if (localCommand.getArgAmount() != args.size()){
//            throw new IncorrectCommandArgumentException("Введено неправильное количество аргументов команды");
//        }
//        if (localCommand instanceof UpdateCommand){
//            if (!IdCollection.idCollection.contains(ArgumentParser.parseInteger(args.get(0)))){
//                throw new IdUnavailableException("Такого id не существует");
//            }
//        }
//        if (localCommand.getInputElement()){
//            WorkerConsoleReader workerConsoleReader = new WorkerConsoleReader(new Scanner(System.in), new ConsolePrinter());
//            localCommand.setAdditionalArg(workerConsoleReader.createWorkerObject());
//        }
//        if (localCommand instanceof ExecuteScriptCommand){
//            localCommand.setResolver(new FileCommandResolver(commandFactory));
//        }
//        return localCommand;
//    }
//}
//
