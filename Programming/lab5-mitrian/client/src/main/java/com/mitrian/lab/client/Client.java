package com.mitrian.lab.client;

import com.mitrian.lab.client.resolvers.SingleCommandResolver;
import com.mitrian.lab.client.ui.Console;
import com.mitrian.lab.common.commands.resolvers.Resolver;
import com.mitrian.lab.common.commands.utils.FileManager;
import com.mitrian.lab.common.dao.Dao;
import com.mitrian.lab.common.data.Worker;
import com.mitrian.lab.common.exceptions.FileException;
import com.mitrian.lab.common.exceptions.impl.file.FileReadableException;
import com.mitrian.lab.common.exceptions.impl.file.FileWritableException;
import com.mitrian.lab.common.exceptions.impl.file.NoSuchFileException;
import com.mitrian.lab.common.exetutors.Executor;
import com.mitrian.lab.common.utils.ConsolePriner;
import com.mitrian.lab.common.utils.Printer;
import com.mitrian.lab.server.collection.Collection;
import com.mitrian.lab.server.collection.CollectionImpl;
import com.mitrian.lab.server.dao.WorkerDaoImpl;
import com.mitrian.lab.server.executors.SingleCommandExecutor;

import java.io.File;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Printer printer = new ConsolePriner();
        // Collection<Worker> workerCollection = new CollectionImpl();
        Collection<Worker> workerCollection = new CollectionImpl();
        Dao<Worker> workerDao = new WorkerDaoImpl(workerCollection);

        CommandFactory commandFactory = new CommandFactory(printer);
        Resolver resolver = new SingleCommandResolver(commandFactory);
        Executor executor = new SingleCommandExecutor(workerDao);

        Console console = new Console(printer, scanner, resolver, executor);


        console.run();

    }
}
