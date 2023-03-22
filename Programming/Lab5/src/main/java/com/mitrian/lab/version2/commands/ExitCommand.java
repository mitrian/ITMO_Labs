package com.mitrian.lab.version2.commands;

import com.mitrian.lab.version2.utils.readers.ConsoleCommandReader;
import com.mitrian.lab.version2.Receiver;
import com.mitrian.lab.version2.utils.AbstractCommand;
import com.mitrian.lab.version2.utils.Printer;

public class ExitCommand extends AbstractCommand {
    private Receiver receiver;

    private Printer printer;
    private ConsoleCommandReader commandReader;

    private String descriptor = "завершить программу (без сохранения в файл)";

    private String name = "exit";

    public ExitCommand(Receiver receiver, ConsoleCommandReader commandReader, Printer printer){
        this.receiver = receiver;
        this.commandReader = commandReader;
        this.printer = printer;
    }

    @Override
    public boolean execute() {
        try{
            if ("".equals(commandReader.readLine()[1])){
                receiver.exit();
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

    @Override
    public String getName() {return name;}
}
