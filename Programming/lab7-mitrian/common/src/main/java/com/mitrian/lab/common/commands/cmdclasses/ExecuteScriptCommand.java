package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.utils.FileManager;
import com.mitrian.lab.common.utils.Printer;

import java.io.File;
import java.util.List;

/**
 * Command Class for executing script
 */
public class ExecuteScriptCommand extends AbstractCommand {


    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param arguments param for initialize arguments field
     */
    public ExecuteScriptCommand(Printer printer, List<String> arguments){
        super(printer, 1, arguments, false);
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public boolean execute()  {
        try {
            File file = FileManager.getFileByName(arguments.get(0));
            executionResult.appendNewLine(executor.execute(file.getName(),resolver.resolve(file)).toString());
            return true;
        } catch ( Exception e){
            executionResult.appendNewLine(e.getMessage());
            return false;
        }
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "execute_script";
    }
}