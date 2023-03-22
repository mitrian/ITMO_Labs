package com.mitrian.lab.version1.commands;

import com.mitrian.lab.version1.LinkedListCollection;
import com.mitrian.lab.version1.source.Worker;
import com.mitrian.lab.version1.utils.AbstractCommand;
import com.mitrian.lab.version1.utils.Printer;

import java.util.List;

public class RemoveFirstCommand extends AbstractCommand {
    private String descriptor = "Удалить первый элемент из коллекции";
    private Printer printer;
    private String arguments;
    private List<Worker> workersCollection;

    public RemoveFirstCommand(List<Worker> workersCollection, String arguments, Printer printer){
        this.arguments = arguments;
        this.workersCollection = workersCollection;
        this.printer = printer;
    }


    @Override
    public boolean execute() {
        try {
            if ("".equals(arguments)){
                if (LinkedListCollection.workersCollection.size()>0) {
                    LinkedListCollection.workersCollection.remove(0);
                    return true;
                } else {
                    return false;
                }
            } else {
                printer.print("Не указывайте аргументы при использовании данной команды");
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }
}
