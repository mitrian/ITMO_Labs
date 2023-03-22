package com.mitrian.lab.version2.commands.managers;

import com.mitrian.lab.version2.ConsoleCommandReader;
import com.mitrian.lab.version2.Receiver;
import com.mitrian.lab.version2.commands.*;
import com.mitrian.lab.version2.utils.AbstractCommand;
import com.mitrian.lab.version2.utils.Printer;

import java.util.HashMap;

public class CommandCollection {
    private final Receiver receiver = new Receiver();
    private ConsoleCommandReader commandReader;

    private Printer printer;

    public CommandCollection(ConsoleCommandReader commandReader, Printer printer){
        this.commandReader = commandReader;
        this.printer = printer;
    }



    public static HashMap<String, AbstractCommand> commandCollection = new HashMap<>();
    public static HashMap<String, String> commandDescription = new HashMap<>();

    public CommandCollection() {
        commandCollection.put("help", new HelpCommand(receiver, commandReader, printer));
        commandCollection.put("info", new InfoCommand(receiver, commandReader, printer));
        commandCollection.put("show", new ShowCommand(receiver, commandReader, printer));
        commandCollection.put("add", new AddCommand(receiver, commandReader, printer));
    }
}
