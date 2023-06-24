package com.mitrian.lab.client;

import com.mitrian.lab.client.resolvers.SingleCommandResolver;
import com.mitrian.lab.client.ui.Console;
import com.mitrian.lab.common.commands.resolvers.Resolver;
import com.mitrian.lab.common.network.UDPClient;
import com.mitrian.lab.common.utils.ConsolePrinter;
import com.mitrian.lab.common.utils.Printer;

import java.util.Scanner;

/**
 * Main class
 */
public class Client {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        Printer printer = new ConsolePrinter();
//        if ( args == null){
//            printer.println("Для запуска укажите файл");
//            System.exit(0);
//        }
//
//        try{
//            Collection<Worker> workerCollection = new CollectionImpl(FileManager.getFileByName(args[0]));
//            Dao<Worker> workerDao = new WorkerDaoImpl(workerCollection);
//            CommandFactory commandFactory = new CommandFactory(printer);
//            Resolver resolver = new SingleCommandResolver(commandFactory);
//            Executor executor = new SingleCommandExecutor(workerDao);
//            Console console = new Console(printer, scanner, resolver, executor);
//            workerCollection.load();
//            printer.println("Файл успешно загружен");
//            console.run();
//
//
//
//        } catch (ArrayIndexOutOfBoundsException e){
//            printer.println("Для запуска укажите файл");
//            System.exit(0);
//        } catch (IOException | IncorrectFieldException | ForcedShutdownException | CommaException | FileFormatException e) {
//            printer.println(e.getMessage());
//        }

        Scanner scanner = new Scanner(System.in);
        Printer printer = new ConsolePrinter();

        if (args == null) {
			printer.println("Please, specify a client port");
			System.exit(0);
		}


        CommandFactory commandFactory = new CommandFactory(printer);
        Resolver resolver = new SingleCommandResolver(commandFactory);


        try (UDPClient client = new UDPClient(Integer.parseInt(args[0]), "localhost", 8080)) {
            Console console = new Console(printer, scanner, resolver, client);
            console.run();


//            AbstractCommand command = (new CommandFactory(new ConsolePrinter())).newCommand("exit", Collections.emptyList());
//            CommandRequest request = new CommandRequest(command);
//            CommandResponse response = client.sendCommandRequest(request);
//            System.out.println(response.getStatus() + ": " + response.getMessage());
        }
        catch (NumberFormatException e) {
            printer.println("Invalid port format");
        }
        catch (Exception e) {
            printer.println(e.getMessage());

//            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
        }

    }
}
