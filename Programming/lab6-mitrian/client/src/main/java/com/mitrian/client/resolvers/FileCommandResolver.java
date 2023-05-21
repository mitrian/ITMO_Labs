//package com.mitrian.client.resolvers;
//
//import com.mitrian.common.commands.factory.CommandFactory;
//import com.mitrian.client.ui.file.WorkerFileReader;
//import com.mitrian.common.commands.AbstractCommand;
//import com.mitrian.common.commands.cmdclass.ExecuteScriptCommand;
//import com.mitrian.common.commands.resolver.Resolver;
//import com.mitrian.common.exceptions.IncorrectFieldException;
//import com.mitrian.common.exceptions.ReaderException;
//import com.mitrian.common.exceptions.impl.commands.CommandNotFoundException;
//import com.mitrian.common.exceptions.impl.file.NoSuchFileException;
//import com.mitrian.common.exceptions.impl.file.ScriptRecursionException;
//
//import java.io.*;
//import java.util.*;
//
///**
// * Class for resolving Command object from file
// */
//public class FileCommandResolver implements Resolver {
//
//    /** Current Command Factory for initialize command object */
//    private final CommandFactory commandFactory;
//    /**
//     * Constructor for initialize fields
//     * @param commandFactory param for initialize commandFactory field
//     */
//    public FileCommandResolver(CommandFactory commandFactory) {
//        this.commandFactory = commandFactory;
//    }
//
//    /**
//     * Resolve commands from file
//     * @param file entry file
//     * @return List of commands for executing
//     * @throws CommandNotFoundException unsupportable command
//     * @throws NoSuchFileException absence of file
//     * @throws ReaderException wrong Worker field
//     */
//    @Override
//    public List<AbstractCommand> resolve(File file) throws CommandNotFoundException, NoSuchFileException, ReaderException, IncorrectFieldException {
//        List<AbstractCommand> commands = new ArrayList<>();
//        try (Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(file)))){
//            WorkerFileReader workerFileReader = new WorkerFileReader(scanner);
//            while (scanner.hasNextLine()){
//                String[] line = scanner.nextLine().trim().split(" ");
//                List<String> args = Arrays.asList(
//                        Arrays.copyOfRange(line,1,line.length)
//                );
//                AbstractCommand localCommand = commandFactory.newCommand(line[0],args);
//                if (localCommand instanceof ExecuteScriptCommand){
//                    localCommand.setResolver(this);
//                }
//                if (localCommand.getInputElement()){
//                    localCommand.setAdditionalArg(workerFileReader.createWorkerObject());
//                }
//                commands.add(localCommand);
//                if (args.size() != localCommand.getArgAmount()){
//                    commands.remove(commands.size()-1);
//                }
//            }
//            return commands;
//        } catch (FileNotFoundException | ScriptRecursionException e) {
//            throw new NoSuchFileException("Такой файл не найден");
//        } catch (IncorrectFieldException e) {
//            throw new IncorrectFieldException(e.getMessage());
//        }
//    }
//}
