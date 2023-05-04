package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.utils.parser.ArgumentParser;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.exceptions.IncorrectFieldException;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

/**
 * Command class to remove collection element by id
 */
public class RemoveByIdCommand extends AbstractCommand {

    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param arguments param for initialize arguments field
     */
    public RemoveByIdCommand(Printer printer, List<String> arguments) {
        super(printer, 1, arguments, false);
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public boolean execute() throws CollectionException {
        try {
            dao.remove(ArgumentParser.parseInteger(arguments.get(0)));
            return true;
        } catch ( IncorrectFieldException e){
            printer.println(e.getMessage());
            return false;
        }
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "remove_by_id";
    }
}
