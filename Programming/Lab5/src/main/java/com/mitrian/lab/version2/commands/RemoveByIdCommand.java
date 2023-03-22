package com.mitrian.lab.version2.commands;

import com.mitrian.lab.version2.ConsoleCommandReader;
import com.mitrian.lab.version2.LinkedListCollection;
import com.mitrian.lab.version2.Receiver;
import com.mitrian.lab.version2.source.Status;
import com.mitrian.lab.version2.source.Worker;
import com.mitrian.lab.version2.commands.managers.ValidatorOfType;
import com.mitrian.lab.version2.utils.AbstractCommand;
import com.mitrian.lab.version2.utils.ConsolePrinter;
import com.mitrian.lab.version2.utils.Printer;
import com.mitrian.lab.version2.utils.exceptions.ForcedShutdownException;

import java.util.List;

public class RemoveByIdCommand extends AbstractCommand {
    Receiver receiver;
    private String descriptor = "обновить значение элемента коллекции, id которого равен заданному";

    private Printer printer;
    private ConsoleCommandReader commandReader;


    private String name = "remove_by_id";

    public RemoveByIdCommand(Receiver receiver, ConsoleCommandReader commandReader, Printer printer){
        this.receiver = receiver;
        this.commandReader = commandReader;
        this.printer = printer;
    }

    @Override
    public boolean execute() throws ForcedShutdownException {
        try{
            if ("".equals(commandReader.readConsoleArguments())){
                printer.print("Для использования данной команды необходимо ввести аргумент");
                return false;
            } else{
                long id;
                try{
                    id = Long.parseLong(commandReader.readConsoleArguments());
                } catch (NumberFormatException e ) {
                    printer.print("Введенное значение id некорректно");
                    do {
                        printer.print("Повторите ввод");
                        receiver.remove_by_id(ValidatorOfType.validationLong());
                    } while (true);

                }
               receiver.remove_by_id(id);
                }
                return true;

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
