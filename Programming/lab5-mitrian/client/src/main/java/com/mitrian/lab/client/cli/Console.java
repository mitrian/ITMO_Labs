package com.mitrian.lab.client.cli;

import com.mitrian.lab.client.resolvers.Resolver;
import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.utils.Printer;

import java.util.Scanner;

public class Console {

    private final Resolver resolver;
    private final Scanner scanner;
    private final Printer printer;

    public Console(Printer printer, Scanner scanner, Resolver resolver) {
        this.resolver = resolver;
        this.printer = printer;
        this.scanner = scanner;
    }

    public void run()
    {
        while (true) {
//            check if not ctrl + d

            String input = scanner.nextLine().trim();

//            parsing command object from user input
            AbstractCommand command = resolver.resolve(input);

            if (command.getInputElement()) {
//                calling worker console reader & setting additional argument for command
            }
        }
    }

}
