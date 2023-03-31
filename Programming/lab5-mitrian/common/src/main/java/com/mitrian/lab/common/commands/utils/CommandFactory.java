package com.mitrian.lab.common.commands.utils;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.impl.AddCommand;
import com.mitrian.lab.common.dao.Dao;
import com.mitrian.lab.common.data.Worker;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

public class CommandFactory {

    public static AbstractCommand newCommand(
            String commandLine,
            Printer printer,
            CommandSource source,
            List<String> arguments,
            Dao<Worker> dao) {

        AbstractCommand command = switch (commandLine) {
            case "add" -> new AddCommand(printer, source, arguments, dao);

//            default -> // throwing an exception if command not exists
        };

        return command;
    }
}
