package com.mitrian.lab.version1.commands;

import com.mitrian.lab.version1.utils.AbstractCommand;
import com.mitrian.lab.version1.utils.Printer;

public class ExitCommand extends AbstractCommand {
    private String descriptor = "завершить программу (без сохранения в файл)";

    private Printer printer;
    private String arguments;

    public ExitCommand(String arguments, Printer printer){
        this.arguments = arguments;
        this.printer = printer;
    }

    @Override
    public boolean execute() {
        try{
            if ("".equals(arguments)){
                System.exit(0);
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
