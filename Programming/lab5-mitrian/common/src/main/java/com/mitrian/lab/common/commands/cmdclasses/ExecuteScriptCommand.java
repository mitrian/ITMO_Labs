package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.utils.FileManager;
import com.mitrian.lab.common.exceptions.FileException;
import com.mitrian.lab.common.exceptions.IncorrectCommandArgumentException;
import com.mitrian.lab.common.exceptions.impl.file.ScriptRecursionException;
import com.mitrian.lab.common.utils.Printer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Command Class for executing script
 */
public class ExecuteScriptCommand extends AbstractCommand {

    /** Current field of executing files */
    private static final Set<String> openFiles = new HashSet<>();
    /** Current name of command field */
    private String name = "execute_script";


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
        try{
            if (arguments.size()!=1){
                throw new IncorrectCommandArgumentException("Введено неправильное количество аргументов");
            }
            try {

                executor.execute(resolver.resolve(FileManager.getFileByName(arguments.get(0))));
                return true;


            } catch ( Exception e){
                printer.println(e.getMessage());
                return false;
            }
        } catch (IncorrectCommandArgumentException e) {
            printer.println(e.getMessage());
            return false;
        }
    }


    /**
     * Getter of name field
     */
    public String getNameOfCommand(){
        return name;
    }
}