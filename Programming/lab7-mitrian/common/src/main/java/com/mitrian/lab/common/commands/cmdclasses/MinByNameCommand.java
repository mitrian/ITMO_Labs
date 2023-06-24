package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.exceptions.impl.collection.CollectionElementException;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

/**
 * Command class for outputting collection element with minimal size of name field
 */
public class MinByNameCommand extends AbstractCommand {

    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param arguments param for initialize arguments field
     */
    public MinByNameCommand(Printer printer, List<String> arguments) {
        super(printer, 0, arguments, false);
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public boolean execute() throws CollectionException {
        try {
            executionResult.appendNewLine(dao.getMinByName().toString());
            return true;
        } catch (CollectionElementException e){
            executionResult.appendNewLine(e.getMessage());
            return false;
        }
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "min_by_id";
    }
}
