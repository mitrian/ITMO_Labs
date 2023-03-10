package com.mitrian.lab.commands;

import com.mitrian.lab.LinkedListCollection;
import com.mitrian.lab.utils.AbstractCommand;
import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.Printer;

public class RemoveFirstCommand extends AbstractCommand {
    private String descriptor = "Удалить первый элемент из коллекции";
    Printer printer = new ConsolePrinter();

    @Override
    public boolean execute(String[] args) {
        printer.print("Не указывайте аргументы при использовании данной команды");
        return false;
    }

    @Override
    public boolean execute() {
        try {
            if (LinkedListCollection.workersCollection.size()>0) {
                LinkedListCollection.workersCollection.remove(0);
                return true;
            } else {
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
