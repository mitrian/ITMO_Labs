package com.mitrian.lab.client.resolvers;

import com.mitrian.lab.client.CommandFactory;
import com.mitrian.lab.client.ui.file.WorkerFileReader;
import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.resolvers.Resolver;
import com.mitrian.lab.common.exceptions.IncorrectFieldException;
import com.mitrian.lab.common.exceptions.ReaderException;
import com.mitrian.lab.common.exceptions.impl.commands.CommandNotFoundException;
import com.mitrian.lab.common.exceptions.impl.file.NoSuchFileException;
import com.mitrian.lab.common.exceptions.impl.file.ScriptRecursionException;

import java.io.*;
import java.util.*;

/**
 * Class for resolving Command object from file
 */
public class FileCommandResolver implements Resolver {

    /** Current Command Factory for initialize command object */
    private final CommandFactory commandFactory;


    /**
     * Constructor for initialize fields
     * @param commandFactory param for initialize commandFactory field
     */
    public FileCommandResolver(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    /**
     * Resolve commands from file
     * @param file entry file
     * @return List of commands for executing
     * @throws CommandNotFoundException unsupportable command
     * @throws NoSuchFileException absence of file
     * @throws ReaderException wrong Worker field
     */
    @Override
    public List<AbstractCommand> resolve(File file) throws CommandNotFoundException, NoSuchFileException, ReaderException, IncorrectFieldException {
        List<AbstractCommand> commands = new ArrayList<>();
        try (Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(file)))){
            WorkerFileReader workerFileReader = new WorkerFileReader(scanner);
            while (scanner.hasNextLine()){
                String[] line = scanner.nextLine().trim().split(" ");
                List<String> args = Arrays.asList(
                        Arrays.copyOfRange(line,1,line.length)
                );
                AbstractCommand localCommand = commandFactory.newCommand(line[0],args);
                if (args.size() != localCommand.getArgAmount()){
                    throw new IncorrectFieldException("Для команды " + localCommand.getNameOfCommand() +
                            " указано неправильное количество аргументов");
                }
                //TODO
                if (localCommand.getInputElement()){
                    localCommand.setAdditionalArg(workerFileReader.createWorkerObject());
                }
                commands.add(localCommand);
            }
            return commands;
        } catch (FileNotFoundException | ScriptRecursionException e) {
            throw new NoSuchFileException("Такой файл не найден");
        } catch (IncorrectFieldException e) {
            throw new IncorrectFieldException(e.getMessage());
        }
    }
}
