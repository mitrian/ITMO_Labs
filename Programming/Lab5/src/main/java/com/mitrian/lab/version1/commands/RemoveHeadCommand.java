package com.mitrian.lab.version1.commands;

import com.mitrian.lab.version1.LinkedListCollection;
import com.mitrian.lab.version1.source.Worker;
import com.mitrian.lab.version1.utils.AbstractCommand;
import com.mitrian.lab.version1.utils.Printer;

import java.util.List;

public class RemoveHeadCommand extends AbstractCommand {
    private String descriptor = "вывести первый элемент коллекции и удалить его";
    private Printer printer;
    private String arguments;
    private List<Worker> workersCollection;

    public RemoveHeadCommand(List<Worker> workersCollection, String arguments, Printer printer){
        this.arguments = arguments;
        this.workersCollection = workersCollection;
    }




    @Override
    public boolean execute() {
        try {
            if ("".equals(arguments)){
                printer.print(LinkedListCollection.workersCollection.get(0).toString());
                LinkedListCollection.workersCollection.remove(0);
                return true;
            } else {
                printer.print("Не указывайте аргументы при использовании данной команды");
                return false;
            }
        } catch (Exception e){
            printer.print("Коллекция пуста");
            return false;
        }
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }
}
