package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.elements.Worker;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.utils.Printer;

import java.io.IOException;
import java.util.List;

/**
 * Command class to update element by input id
 */
public class UpdateCommand extends AbstractCommand {


    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param arguments param for initialize arguments field
     */
    public UpdateCommand(Printer printer, List<String> arguments) {
        super(printer, 1, arguments, true);
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public boolean execute() throws CollectionException, IOException {
        dao.update(Integer.valueOf(arguments.get(0)), (Worker) additionalArg);
        return false;
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "update";
    }
}
