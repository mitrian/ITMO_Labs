package com.mitrian.lab.version2.commands;

import com.mitrian.lab.version2.utils.readers.ConsoleCommandReader;
import com.mitrian.lab.version2.Receiver;
import com.mitrian.lab.version2.source.Worker;
import com.mitrian.lab.version2.utils.AbstractCommand;
import com.mitrian.lab.version2.utils.Printer;

import java.util.List;

public class AddCommand extends AbstractCommand {
    private Printer printer;
    //WorkerInitializer w = new WorkerInitializer(printer);
    private List<Worker> workersCollection;
    private ConsoleCommandReader commandReader;
    private String descriptor = "добавить новый элемент в коллекцию";

    private String name = "add";

    private Receiver receiver;


    public AddCommand(Receiver receiver, ConsoleCommandReader commandReader, Printer printer){
        this.receiver = receiver;
        this.commandReader = commandReader;
        this.printer = printer;
    }

    @Override
    public boolean execute() {
        try {
            if ("".equals(commandReader.readLine()[1])){
                receiver.add();
                return true;
            } else {
                printer.print("Не указывайте аргументы при использовании данной команды");
                return false;
            }
        } catch (Exception e) {
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

