package com.mitrian.lab.version1.commands;

import com.mitrian.lab.version1.LinkedListCollection;
import com.mitrian.lab.version1.source.Worker;
import com.mitrian.lab.version1.utils.AbstractCommand;
import com.mitrian.lab.version1.utils.Printer;

import java.util.List;

public class ClearCommand extends AbstractCommand {
    private String descriptor = "очистить коллекцию";
    private Printer printer;
    private List<Worker> workersCollection;
    private String arguments;

    public ClearCommand(List<Worker> workersCollection, String arguments, Printer printer){
        this.workersCollection = workersCollection;
        this.arguments = arguments;
        this.printer = printer;
    }

    @Override
    public boolean execute() {
        try {
            if ("".equals(arguments)){
                LinkedListCollection.workersCollection.clear();
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
}
