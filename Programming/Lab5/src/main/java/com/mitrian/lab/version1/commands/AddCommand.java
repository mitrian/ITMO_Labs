package com.mitrian.lab.version1.commands;

import com.mitrian.lab.version1.commands.managers.WorkerInitializer;
import com.mitrian.lab.version1.source.Worker;
import com.mitrian.lab.version1.utils.AbstractCommand;
import com.mitrian.lab.version1.utils.Printer;

import java.util.List;

public class AddCommand extends AbstractCommand {
    private Printer printer;
    WorkerInitializer w = new WorkerInitializer(printer);
    private List<Worker> workersCollection;
    private String arguments;
    private String descriptor = "добавить новый элемент в коллекцию";

    public AddCommand(List<Worker> workersCollection, String arguments, Printer printer){
        this.workersCollection = workersCollection;
        this.arguments = arguments;
        this.printer = printer;
    }

    @Override
    public boolean execute() {
        try {
            if ("".equals(arguments)){
                workersCollection.add(w.createWorkerObject());
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
}

