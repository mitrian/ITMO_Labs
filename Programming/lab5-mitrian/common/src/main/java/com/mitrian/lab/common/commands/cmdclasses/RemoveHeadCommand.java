package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.exceptions.IncorrectCommandArgumentException;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

/**
 * Command class to print and remove first element of collection
 */
public class RemoveHeadCommand extends AbstractCommand {

    /** Current name of command field */
    private String name = "remove_head";

    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param arguments param for initialize arguments field
     */
    public RemoveHeadCommand(Printer printer, List<String> arguments) {
        super(printer, 0, arguments, false);
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public boolean execute() throws CollectionException {
        try {
            if (arguments.size() != 0){
                throw new IncorrectCommandArgumentException("Введено неправильное количество аргументов");
            }
            printer.print(dao.removeHead().toString());
            return true;
        } catch (IncorrectCommandArgumentException e){
            printer.println(e.getMessage());
            return false;
        }
    }


    /**
     * Getter of name field
     */
    public String getNameOfCommand(){
        return name;
    }
}
