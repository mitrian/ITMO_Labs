package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.exceptions.IncorrectCommandArgumentException;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

/**
 * Command class to clear collection
 */
public class ClearCommand extends AbstractCommand {

    /** Current name of command field */
    private String name = "clear";


    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param arguments param for initialize arguments field
     */
    public ClearCommand(Printer printer, List<String> arguments) {
        super(printer, 0, arguments, false);
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public boolean execute()  {
        try {
            if (arguments.size()!=0){
                throw new IncorrectCommandArgumentException("Введено неправильное количество аргументов");
            }
            dao.clear();
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
