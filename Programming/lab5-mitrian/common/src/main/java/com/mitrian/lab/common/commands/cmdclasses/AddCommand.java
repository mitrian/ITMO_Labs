package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.exceptions.IncorrectCommandArgumentException;
import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.elements.Worker;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

/**
 * Command class for adding element into collection
 */
public class AddCommand extends AbstractCommand {


    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param arguments param for initialize arguments field
     */
    public AddCommand(Printer printer, List<String> arguments) {
        super(printer, 0, arguments, true);
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public boolean execute() {
            Worker worker = (Worker) additionalArg;
            dao.add(worker);
            return true;
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "add";
    }

}
