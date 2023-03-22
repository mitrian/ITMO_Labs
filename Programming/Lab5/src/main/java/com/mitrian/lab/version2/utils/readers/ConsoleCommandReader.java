package com.mitrian.lab.version2.utils.readers;

import com.mitrian.lab.version2.commands.managers.CommandCollection;
import com.mitrian.lab.version2.utils.Printer;


import java.util.Scanner;

public class ConsoleCommandReader {
    private Printer printer;
    private Scanner scanner = new Scanner(System.in);
    CommandParser parser = new CommandParser(new CommandCollection());

    public ConsoleCommandReader(Printer printer){
        this.printer = printer;
    }

    public String[] readLine(){
        printer.print("Введите команду: ");
        while (true){
            return parser.parseCommand(scanner.nextLine());
        }
    }
}
