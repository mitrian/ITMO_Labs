package com.mitrian.common.commands.factory;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.cmdclass.*;
import com.mitrian.common.exceptions.impl.commands.CommandNotFoundException;

import java.util.List;


/**
 * Class for creating command object from user input
 */
public class CommandFactory {

    /**
     * Creating command object from string format
     * @param commandLine param for command in string format
     * @param arguments param for initialize command arguments
     * @return command object
     * @throws CommandNotFoundException unsupportable command
     */
    public static AbstractCommand newCommand(String commandLine, List<String> arguments) throws CommandNotFoundException {

//        TODO: to be remoworked
        AbstractCommand command = switch (commandLine) {
            case "add" -> new AddCommand(arguments);
            case "clear" -> new ClearCommand( arguments);
            case "exit" ->  new ExitCommand(arguments);
            case "filter_by_status" -> new FilterByStatusCommand( arguments);
            case "help" -> new HelpCommand(arguments);
            case "min_by_name" -> new MinByNameCommand(arguments);
            case "remove_by_id" -> new RemoveByIdCommand(arguments);
            case "remove_first" -> new RemoveFirstCommand(arguments);
            case "remove_greater" -> new RemoveGreaterCommand(arguments);
            case "remove_head" -> new RemoveHeadCommand(arguments);
            case "show" -> new ShowCommand(arguments);
            case "execute_script" -> new ExecuteScriptCommand(arguments);
            case "info" -> new InfoCommand(arguments);
            case "save" -> new SaveCommand(arguments);
            case "update" -> new UpdateCommand(arguments);
            case "print_unique_person" -> new PrintUniquePersonCommand(arguments);


            default -> throw new CommandNotFoundException("Такой команды не существует");

        };

        return command;
    }
}
