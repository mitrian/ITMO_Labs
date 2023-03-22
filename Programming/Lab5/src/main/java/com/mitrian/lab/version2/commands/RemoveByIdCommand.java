package com.mitrian.lab.version2.commands;

import com.mitrian.lab.version2.utils.readers.ConsoleCommandReader;
import com.mitrian.lab.version2.Receiver;
import com.mitrian.lab.version2.commands.managers.ValidatorOfType;
import com.mitrian.lab.version2.utils.AbstractCommand;
import com.mitrian.lab.version2.utils.Printer;
import com.mitrian.lab.version2.utils.exceptions.ForcedShutdownException;

public class RemoveByIdCommand extends AbstractCommand {
    Receiver receiver;
    private String descriptor = "обновить значение элемента коллекции, id которого равен заданному";

    private Printer printer;
    private ConsoleCommandReader commandReader;

    private String[] line;
    private String name = "remove_by_id";

    public RemoveByIdCommand(Receiver receiver, ConsoleCommandReader commandReader, Printer printer){
        this.receiver = receiver;
        this.commandReader = commandReader;
        this.printer = printer;
    }

    @Override
    public boolean execute() throws ForcedShutdownException {
        try{
            line = commandReader.readLine();
            if ("".equals(line[1])){
                printer.print("Для использования данной команды необходимо ввести аргумент");
                return false;
            } else{
                long id;
                try{
                    id = Long.parseLong(line[1]);
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
