package com.mitrian.lab.version2.commands;

import com.mitrian.lab.version2.utils.readers.ConsoleCommandReader;
import com.mitrian.lab.version2.Receiver;
import com.mitrian.lab.version2.utils.AbstractCommand;
import com.mitrian.lab.version2.utils.Printer;

public class RemoveHeadCommand extends AbstractCommand {
    private String descriptor = "вывести первый элемент коллекции и удалить его";
    private Printer printer;
    private ConsoleCommandReader commandReader;

    private Receiver receiver;
    private String name = "remove_head";

    public RemoveHeadCommand(Receiver receiver, ConsoleCommandReader commandReader, Printer printer){
        this.commandReader = commandReader;
        this.receiver = receiver;
    }




    @Override
    public boolean execute() {
        try {
            if ("".equals(commandReader.readLine()[1])){
                printer.print(receiver.removeHead());
                return true;
            } else {
                printer.print("Не указывайте аргументы при использовании данной команды");
                return false;
            }
        } catch (Exception e){
            printer.print("Коллекция пуста");
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
