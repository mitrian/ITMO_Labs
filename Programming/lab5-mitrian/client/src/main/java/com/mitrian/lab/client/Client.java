package com.mitrian.lab.client;

import com.mitrian.lab.client.resolvers.SingleCommandResolver;
import com.mitrian.lab.client.ui.Console;
import com.mitrian.lab.common.commands.resolvers.Resolver;
import com.mitrian.lab.common.commands.utils.FileManager;
import com.mitrian.lab.common.dao.Dao;
import com.mitrian.lab.common.elements.Worker;
import com.mitrian.lab.common.exceptions.FileException;
import com.mitrian.lab.common.exetutors.Executor;
import com.mitrian.lab.common.utils.ConsolePriner;
import com.mitrian.lab.common.utils.Printer;
import com.mitrian.lab.server.collection.Collection;
import com.mitrian.lab.server.collection.CollectionImpl;
import com.mitrian.lab.server.dao.WorkerDaoImpl;
import com.mitrian.lab.server.executors.SingleCommandExecutor;
import com.mitrian.lab.server.file.csv.CsvLoader;

import java.io.*;
import java.util.Scanner;

/**
 * Main class
 */
public class Client {

    public static void main(String[] args) throws FileException {
        Scanner scanner = new Scanner(System.in);
        Printer printer = new ConsolePriner();
        // Collection<Worker> workerCollection = new CollectionImpl();
        Collection<Worker> workerCollection = new CollectionImpl(FileManager.getFileByName(args[0]));
        Dao<Worker> workerDao = new WorkerDaoImpl(workerCollection);

        CommandFactory commandFactory = new CommandFactory(printer);
        Resolver resolver = new SingleCommandResolver(commandFactory);
        Executor executor = new SingleCommandExecutor(workerDao);

        Console console = new Console(printer, scanner, resolver, executor);
//        try (Reader reader = new FileReader(args[0])){
//            new CsvLoader((FileManager.getFileByName(args[0]))).load(workerCollection.getAllElements());
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        console.run();

    }
}
