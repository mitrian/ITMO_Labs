package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.elements.Worker;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.exceptions.IncorrectCommandArgumentException;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

/**
 * Command class to remove collection element, witch is bigger than input element
 */
public class RemoveGreaterCommand extends AbstractCommand {

    /** Current name of command field */
    private String name = "remove_greater";

    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param arguments param for initialize arguments field
     */
    public RemoveGreaterCommand(Printer printer, List<String> arguments) {
        super(printer, 0, arguments, true);
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public boolean execute() throws CollectionException {
        Worker worker = (Worker) additionalArg;
        dao.removeGreater(worker);
        return true;
    }


    /**
     * Getter of name field
     */
    public String getNameOfCommand(){
        return name;
    }
}
