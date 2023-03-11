package com.mitrian.lab.commands;

import com.mitrian.lab.commands.managers.Initializer;
import com.mitrian.lab.utils.AbstractCommand;
import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.Printer;
import com.mitrian.lab.utils.exceptions.ForcedShutdownException;

import java.util.Arrays;

public class AddCommand extends AbstractCommand {
    Printer printer = new ConsolePrinter();
    private String descriptor = "добавить новый элемент в коллекцию";

    @Override
    public boolean execute(String arguments) {
        printer.print("Не указывайте аргументы при использовании данной команды");
        return false;
    }

    @Override
    public boolean execute() {
        try {
            Initializer.createWorkerObject();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }
}

