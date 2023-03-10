package com.mitrian.lab.commands;

import com.mitrian.lab.LinkedListCollection;
import com.mitrian.lab.utils.AbstractCommand;
import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.Printer;

public class ClearCommand extends AbstractCommand {
    private String descriptor = "очистить коллекцию";
    static Printer printer = new ConsolePrinter();

    public ClearCommand(){
        super();
    }

    @Override
    public boolean execute(String[] args) {
        printer.print("Не указывайте аргументы при использовании данной команды");
        return false;
    }

    @Override
    public boolean execute() {
        try {
            LinkedListCollection.workersCollection.clear();
            return true;
        } catch (Exception e){
            return false;
        }

    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }
}
