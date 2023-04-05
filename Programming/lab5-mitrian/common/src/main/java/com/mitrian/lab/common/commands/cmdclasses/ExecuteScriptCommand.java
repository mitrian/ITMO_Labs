package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.utils.CommandSource;
import com.mitrian.lab.common.commands.utils.FileManager;
import com.mitrian.lab.common.exceptions.IncorrectCommandArgumentException;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

/**
 * Command Class for executing script
 */
public class ExecuteScriptCommand extends AbstractCommand {

    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param source param for initialize source field
     * @param arguments param for initialize arguments field
     */
    public ExecuteScriptCommand(Printer printer, CommandSource source, List<String> arguments) {
        super(printer, source, arguments, false);
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
            } catch (Exception e){
                printer.println("afdfa");
                printer.println(e.getMessage());
                return false;
            }
        } catch (IncorrectCommandArgumentException e) {
            printer.println(e.getMessage());
            return false;
        }
    }
}
