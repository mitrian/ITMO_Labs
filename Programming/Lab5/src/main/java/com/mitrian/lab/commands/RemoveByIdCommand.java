package com.mitrian.lab.commands;

import com.mitrian.lab.utils.AbstractCommand;
import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.Printer;

public class RemoveByIdCommand extends AbstractCommand {
    private String descriptor = "обновить значение элемента коллекции, id которого равен заданному";

    Printer printer = new ConsolePrinter();

    @Override
    public boolean execute(String[] args) {
        return false;
    }

    @Override
    public boolean execute() {
        printer.print("Для выполнения данной команды требуется ввести аргумент");
        return false;
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }
}
