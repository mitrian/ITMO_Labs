package com.mitrian.lab.commands;

import com.mitrian.lab.utils.AbstractCommand;
import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.Printer;

public class ExitCommand extends AbstractCommand {
    private String descriptor = "завершить программу (без сохранения в файл)";

    Printer printer = new ConsolePrinter(){};

    @Override
    public boolean execute(String args) {
        printer.print("Не указывайте аргументы при использовании данной команды");
        return false;
    }

    @Override
    public boolean execute() {
        try{
            System.exit(0);
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
