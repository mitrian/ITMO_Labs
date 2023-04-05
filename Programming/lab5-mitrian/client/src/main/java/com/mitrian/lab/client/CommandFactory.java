package com.mitrian.lab.client;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.cmdclasses.*;
import com.mitrian.lab.common.commands.utils.CommandSource;
import com.mitrian.lab.common.exceptions.impl.commands.CommandNotFoundException;
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
     * @param source param for initialize source command
     * @param arguments param for initialize command arguments
     * @return command object
     * @throws CommandNotFoundException unsupportable command
     */
    public AbstractCommand newCommand(
            String commandLine,
            CommandSource source,
            List<String> arguments) throws CommandNotFoundException {

        AbstractCommand command = switch (commandLine) {
            case "add" -> new AddCommand(printer, source, arguments);
            case "clear" -> new ClearCommand(printer, source, arguments);
            case "exit" ->  new ExitCommand(printer, source, arguments);
            case "filter_by_status" -> new FilterByStatusCommand(printer, source, arguments);
            case "help" -> new HelpCommand(printer, source, arguments);
            case "min_by_name" -> new MinByNameCommand(printer, source, arguments);
            case "remove_by_id id" -> new RemoveByIdCommand(printer, source, arguments);
            case "remove_first" -> new RemoveFirstCommand(printer, source, arguments);
            case "remove_greater" -> new RemoveFirstCommand(printer, source, arguments);
            case "remove_head" -> new RemoveHeadCommand(printer, source, arguments);
            case "show" -> new ShowCommand(printer,source, arguments);
            case "execute_script" -> new ExecuteScriptCommand(printer, source, arguments);
            case "info" -> new InfoCommand(printer, source, arguments);

            default -> throw new CommandNotFoundException("Такой команды не существует");

            // TODO: dodelay, dolbyob
        };

        return command;
    }
}
