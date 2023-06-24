package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.utils.Printer;

import java.io.IOException;
import java.util.List;

public class SaveCommand extends AbstractCommand {

    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param arguments param for initialize arguments field
     */
    public SaveCommand(Printer printer, List<String> arguments) {
        super(printer, 0, arguments, false);
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public boolean execute() throws CollectionException {
        try {
            dao.save();
            executionResult.appendNewLine("Save succeed");
            return true;
        }
        catch (IOException e) {
            executionResult.appendNewLine(e.getMessage());
            return false;
        }
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "save";
    }
}
