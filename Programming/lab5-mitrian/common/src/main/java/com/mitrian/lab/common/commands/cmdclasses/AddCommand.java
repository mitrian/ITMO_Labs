package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.exceptions.IncorrectCommandArgumentException;
import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.utils.CommandSource;
import com.mitrian.lab.common.data.Worker;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

/**
 * Command class for adding element into collection
 */
public class AddCommand extends AbstractCommand {

    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param source param for initialize source field
     * @param arguments param for initialize arguments field
     */
    public AddCommand(Printer printer, CommandSource source, List<String> arguments) {
        super(printer, source, arguments, true);
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public boolean execute() {
        try {
            if (arguments.size()!=0){
                throw new IncorrectCommandArgumentException("Введено неправильное количество аргументов");
            }
            Worker worker = (Worker) additionalArg;
//        if worker exists
            dao.add(worker);
            return true;
        } catch (IncorrectCommandArgumentException e){
            printer.println(e.getMessage());
            return false;
        }

    }

}
