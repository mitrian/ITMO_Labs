package com.mitrian.lab.commands;

import com.mitrian.lab.LinkedListCollection;
import com.mitrian.lab.utils.AbstractCommand;
import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.Printer;

public class RemoveHeadCommand extends AbstractCommand {
    private String descriptor = "вывести первый элемент коллекции и удалить его";
    Printer printer = new ConsolePrinter();


    @Override
    public boolean execute(String args) {
        printer.print("Не указывайте аргументы при использовании данной команды");
        return false;
    }

    @Override
    public boolean execute() {
        try {
            printer.print(LinkedListCollection.workersCollection.get(0).toString());
            LinkedListCollection.workersCollection.remove(0);
        } catch (Exception e){
            printer.print("Коллекция пуста");
            return false;
        }
        return true;
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }
}
