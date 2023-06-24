package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

/**
 * Command class to clear collection
 */
public class ClearCommand extends AbstractCommand {


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
        dao.clear();
        executionResult.appendNewLine("Successfully cleared collection");
        return true;
    }




    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "clear";
    }

}
