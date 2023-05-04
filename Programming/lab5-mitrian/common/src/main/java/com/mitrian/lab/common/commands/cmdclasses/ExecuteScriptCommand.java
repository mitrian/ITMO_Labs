package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.utils.FileManager;
import com.mitrian.lab.common.exceptions.FileException;
import com.mitrian.lab.common.exceptions.IncorrectCommandArgumentException;
import com.mitrian.lab.common.exceptions.impl.file.ScriptRecursionException;
import com.mitrian.lab.common.utils.Printer;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            return executor.execute(file.getName(),resolver.resolve(file));
        } catch ( Exception e){
            printer.println(e.getMessage());
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