package com.mitrian.lab.commands;

import com.mitrian.lab.LinkedListCollection;
import com.mitrian.lab.utils.AbstractCommand;
import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.Printer;

public class RemoveFirstCommand extends AbstractCommand {
    Printer printer = new ConsolePrinter();

    @Override
    public boolean execute(String[] args) {
        printer.print("Не указывайте аргументы при использовании данной команды");
        return false;
    }

    @Override
    public boolean execute() {
        try {
            LinkedListCollection.WorkersCollection.remove(0);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public String getDescriptor() {
        return null;
    }
}
