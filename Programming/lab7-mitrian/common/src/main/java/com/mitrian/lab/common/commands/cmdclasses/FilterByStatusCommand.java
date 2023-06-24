package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.elements.Status;
import com.mitrian.lab.common.elements.Worker;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

/**
 * Command class to filter collection elements by Status field
 */
public class FilterByStatusCommand extends AbstractCommand {


    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param arguments param for initialize arguments field
     */
    public FilterByStatusCommand(Printer printer, List<String> arguments) {
        super(printer, 1, arguments, false);
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public boolean execute() throws CollectionException {
        for (Worker element: dao.filterByStatus(Status.valueOf(arguments.get(0)))){
            executionResult.appendNewLine(element.toString());
        }
        return true;
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "filter_by_id";
    }

}
