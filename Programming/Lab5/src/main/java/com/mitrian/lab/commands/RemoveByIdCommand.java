package com.mitrian.lab.commands;

import com.mitrian.lab.LinkedListCollection;
import com.mitrian.lab.commands.managers.ValidatorOfType;
import com.mitrian.lab.utils.AbstractCommand;
import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.ExtraCheck;
import com.mitrian.lab.utils.Printer;

public class RemoveByIdCommand extends AbstractCommand {
    private String descriptor = "обновить значение элемента коллекции, id которого равен заданному";

    Printer printer = new ConsolePrinter();

    @Override
    public boolean execute(String[] args) {
        try{
            int id;
            try{
                id = Integer.parseInt(args[0]);
            } catch (NumberFormatException e ) {
                printer.print("Введенное значение id некорректно");

                do {
                    printer.print("Повторите ввод");

                    id = ValidatorOfType.validation(0, ExtraCheck.NOTHING, ExtraCheck.NOTHING);

                } while (true);
            }
                for (int i = 0; i<LinkedListCollection.workersCollection.size(); i++){
                    if (LinkedListCollection.workersCollection.get(i).getId()==id){
                        LinkedListCollection.workersCollection.remove(i);
                        break;
                    }
                }

                return true;

        } catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean execute() {
        printer.print("Для выполнения данной команды требуется ввести аргумент");
        return false;
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }
}
