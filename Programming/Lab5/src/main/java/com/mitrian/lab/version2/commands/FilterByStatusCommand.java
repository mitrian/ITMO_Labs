package com.mitrian.lab.version2.commands;

import com.mitrian.lab.version1.source.Status;
import com.mitrian.lab.version2.ConsoleCommandReader;
import com.mitrian.lab.version2.Receiver;
import com.mitrian.lab.version2.utils.AbstractCommand;
import com.mitrian.lab.version2.utils.Printer;
import com.mitrian.lab.version2.utils.exceptions.ForcedShutdownException;

public class FilterByStatusCommand extends AbstractCommand {

    private String descriptor = "вывести элементы";
    private Printer printer;
    private ConsoleCommandReader commandReader;
    private String name = "info";
    private Receiver receiver;

    public FilterByStatusCommand(Receiver receiver, ConsoleCommandReader commandReader, Printer printer){
        this.receiver = receiver;
        this.commandReader = commandReader;
        this.printer = printer;
    }

    @Override
    public boolean execute() throws ForcedShutdownException {
        try{
            if (!"".equals(commandReader.readConsoleCommand())){
                printer.print(receiver.filterByStatus(Status.valueOf(commandReader.readConsoleArguments())));
                return true;
            } else {
                printer.print("Для использования данной команды необходимо ввести аргумент");
                return false;
            }

        } catch (Exception e){
            return false;
        }
    }

    @Override
    public String getDescriptor() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
