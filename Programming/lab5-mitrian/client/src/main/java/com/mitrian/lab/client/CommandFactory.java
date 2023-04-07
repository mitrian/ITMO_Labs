package com.mitrian.lab.client;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.cmdclasses.*;
import com.mitrian.lab.common.exceptions.impl.commands.CommandNotFoundException;
import com.mitrian.lab.common.exceptions.impl.file.ScriptRecursionException;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;


/**
 * Class for creating command object from user input
 */
public class CommandFactory {

    /** Current printer field*/
    private final Printer printer;


    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     */
    public CommandFactory(Printer printer) {
        this.printer = printer;
    }


    /**
     * Creating command object from string format
     * @param commandLine param for command in string format
     * @param arguments param for initialize command arguments
     * @return command object
     * @throws CommandNotFoundException unsupportable command
     */
    public AbstractCommand newCommand(String commandLine, List<String> arguments) throws CommandNotFoundException, ScriptRecursionException {

        AbstractCommand command = switch (commandLine) {
            case "add" -> new AddCommand(printer, arguments);
            case "clear" -> new ClearCommand(printer, arguments);
            case "exit" ->  new ExitCommand(printer, arguments);
            case "filter_by_status" -> new FilterByStatusCommand(printer, arguments);
            case "help" -> new HelpCommand(printer, arguments);
            case "min_by_name" -> new MinByNameCommand(printer, arguments);
            case "remove_by_id" -> new RemoveByIdCommand(printer, arguments);
            case "remove_first" -> new RemoveFirstCommand(printer, arguments);
            case "remove_greater" -> new RemoveFirstCommand(printer, arguments);
            case "remove_head" -> new RemoveHeadCommand(printer, arguments);
            case "show" -> new ShowCommand(printer, arguments);
            case "execute_script" -> new ExecuteScriptCommand(printer, arguments);
            case "info" -> new InfoCommand(printer, arguments);
            case "save" -> new SaveCommand(printer, arguments);
            case "update" -> new UpdateCommand(printer, arguments);

            default -> throw new CommandNotFoundException("Такой команды не существует");

        };

        return command;
    }
}
