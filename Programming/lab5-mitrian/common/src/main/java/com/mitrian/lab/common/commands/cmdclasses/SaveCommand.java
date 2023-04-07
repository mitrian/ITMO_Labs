package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.utils.Printer;

import java.io.IOException;
import java.util.List;

public class SaveCommand extends AbstractCommand {

    /** Current name of command field */
    private String name = "save";

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
    public boolean execute() throws CollectionException, IOException {
        dao.save();
        return false;
    }


    /**
     * Getter of name field
     */
    public String getNameOfCommand(){
        return name;
    }
}
