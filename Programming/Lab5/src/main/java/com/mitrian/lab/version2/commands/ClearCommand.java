package com.mitrian.lab.version2.commands;

import com.mitrian.lab.version2.ConsoleCommandReader;
import com.mitrian.lab.version2.LinkedListCollection;
import com.mitrian.lab.version2.Receiver;
import com.mitrian.lab.version2.source.Worker;
import com.mitrian.lab.version2.utils.AbstractCommand;
import com.mitrian.lab.version2.utils.Printer;

import java.util.List;

public class ClearCommand extends AbstractCommand {
    private Receiver receiver;
    private String descriptor = "очистить коллекцию";
    private Printer printer;
    private List<Worker> workersCollection;
    private ConsoleCommandReader commandReader;

    private String name = "clear";

    public ClearCommand(Receiver receiver, ConsoleCommandReader commandReader, Printer printer){
        this.receiver = receiver;
        this.commandReader = commandReader;
        this.printer = printer;
    }

    @Override
    public boolean execute() {
        try {
            if ("".equals(commandReader.readConsoleArguments())){
                receiver.clear();
                return true;
            } else {
                printer.print("Не указывайте аргументы при использовании данной команды");
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }

    @Override
    public String getName() {return name;}
}
