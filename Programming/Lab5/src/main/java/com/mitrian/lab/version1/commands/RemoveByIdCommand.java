package com.mitrian.lab.version1.commands;

import com.mitrian.lab.version1.LinkedListCollection;
import com.mitrian.lab.version1.commands.managers.ValidatorOfType;
import com.mitrian.lab.version1.source.Worker;
import com.mitrian.lab.version1.utils.AbstractCommand;
import com.mitrian.lab.version1.utils.ConsolePrinter;
import com.mitrian.lab.version1.utils.Printer;

import java.util.List;

public class RemoveByIdCommand extends AbstractCommand {
    private String descriptor = "обновить значение элемента коллекции, id которого равен заданному";

    Printer printer = new ConsolePrinter();
    private String arguments;
    private List<Worker> workersCollection;

    public RemoveByIdCommand(List<Worker> workersCollection, String arguments, Printer printer){
        this.arguments = arguments;
        this.workersCollection = workersCollection;
        this.printer = printer;
    }

    @Override
    public boolean execute() {
        try{
            if ("".equals(arguments)){
                printer.print("Для использования данной команды необходимо ввести аргумент");
                return false;
            } else{
                long id;
                try{
                    id = Long.parseLong(arguments);
                } catch (NumberFormatException e ) {
                    printer.print("Введенное значение id некорректно");

                    do {
                        printer.print("Повторите ввод");

                        id = ValidatorOfType.validationLong();

                    } while (true);
                }
                for (int i = 0; i<LinkedListCollection.workersCollection.size(); i++){
                    if (LinkedListCollection.workersCollection.get(i).getId()==id){
                        LinkedListCollection.workersCollection.remove(i);
                        break;
                    }
                }
                return true;
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
