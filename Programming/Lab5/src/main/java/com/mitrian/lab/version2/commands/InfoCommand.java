package com.mitrian.lab.version2.commands;

import com.mitrian.lab.version2.utils.readers.ConsoleCommandReader;
import com.mitrian.lab.version2.Receiver;
import com.mitrian.lab.version2.utils.AbstractCommand;
import com.mitrian.lab.version2.utils.Printer;
import com.mitrian.lab.version2.utils.exceptions.ForcedShutdownException;

public class InfoCommand extends AbstractCommand {
    private String descriptor = "вывести информацию о коллекции";
    private Printer printer;
    private ConsoleCommandReader commandReader;
    private String name = "info";
    private Receiver receiver;

    public  InfoCommand(Receiver receiver, ConsoleCommandReader commandReader, Printer printer){
        this.receiver = receiver;
        this.commandReader = commandReader;
        this.printer = printer;
    }
    @Override
    public boolean execute() throws ForcedShutdownException {
        try{
            if ("".equals(commandReader.readLine()[1])){
                printer.print(receiver.info());
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
        return this.descriptor;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
