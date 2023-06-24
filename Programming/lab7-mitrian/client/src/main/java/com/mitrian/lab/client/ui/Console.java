package com.mitrian.lab.client.ui;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.cmdclasses.ExitCommand;
import com.mitrian.lab.common.commands.cmdclasses.SaveCommand;
import com.mitrian.lab.common.commands.resolvers.Resolver;
import com.mitrian.lab.common.exceptions.ForcedShutdownException;
import com.mitrian.lab.common.network.UDPClient;
import com.mitrian.lab.common.network.model.impl.command.CommandRequest;
import com.mitrian.lab.common.network.model.impl.command.CommandResponse;
import com.mitrian.lab.common.utils.Printer;
import sun.misc.Signal;

import java.util.Scanner;

/**
 * Class for running this app from console
 */
public class Console {

    /** Current resolver field */
    private final Resolver resolver;
    /** Current scanner field */
    private final Scanner scanner;
    /** Current printer field */
    private final Printer printer;

    /**
     * UDP client instance
     */
    private final UDPClient udpClient;


    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param scanner param for initialize scanner field
     * @param resolver param for initialize resolver field
     * @param udpClient udp client instance field
//     * @param executor param for initialize executor field
     */
    public Console(Printer printer, Scanner scanner, Resolver resolver, UDPClient udpClient) {
        this.resolver = resolver;
        this.printer = printer;
        this.scanner = scanner;
        this.udpClient = udpClient;
    }


    /**
     * running app from console
     */
    public void run() throws ForcedShutdownException {
        Signal.handle(new Signal("INT"), (handler)->{
                printer.println("CTRL+C caught: system shutdown initiated");
                System.exit(0);
        });

        while (true) {

            if (!scanner.hasNextLine()){
                throw new ForcedShutdownException("Не надо так");
            }

            String input = scanner.nextLine().trim();
            try {
    //             TODO в реквест

                AbstractCommand command = resolver.resolve(input);

                if (command instanceof SaveCommand) {
                    printer.println("You are not permitted to use 'save' command on the client side");
                    continue;
                }

                CommandRequest request = new CommandRequest(command);
                CommandResponse response = udpClient.sendCommandRequest(request);

                printer.println(response.getMessage());
                if (command instanceof ExitCommand) System.exit(0);
            } catch (Exception e) {
                printer.println(e.getMessage());
            }
        }
    }

}
