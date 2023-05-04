package com.mitrian.lab.client;

import com.mitrian.lab.client.resolvers.SingleCommandResolver;
import com.mitrian.lab.client.ui.ClientApp;
import com.mitrian.lab.common.commands.resolvers.Resolver;
import com.mitrian.lab.common.exceptions.FileException;
import com.mitrian.lab.common.exceptions.ForcedShutdownException;
import com.mitrian.lab.common.utils.ConsolePrinter;
import com.mitrian.lab.common.utils.Printer;

import java.util.Scanner;


/**
 * Main class
 */
public class Client {

    public static void main(String[] args) throws FileException {
          Scanner scanner = new Scanner(System.in);
          Printer printer = new ConsolePrinter();
        if ( args == null){
            printer.println("Для запуска укажите файл");
            System.exit(0);
        }



        try{
            CommandFactory commandFactory = new CommandFactory(printer);
            Resolver resolver = new SingleCommandResolver(commandFactory);
            ClientApp clientApp = new ClientApp(printer, scanner, resolver);
            printer.println("Файл успешно загружен");
            clientApp.run();



        } catch (ArrayIndexOutOfBoundsException e){
            printer.println("Для запуска укажите файл");
            System.exit(0);
        } catch (  ForcedShutdownException e) {
            printer.println(e.getMessage());
        }

    }
}
