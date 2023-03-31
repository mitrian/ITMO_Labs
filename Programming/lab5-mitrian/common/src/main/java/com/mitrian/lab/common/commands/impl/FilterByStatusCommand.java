package com.mitrian.lab.common.commands.impl;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.utils.CommandSource;
import com.mitrian.lab.common.dao.Dao;
import com.mitrian.lab.common.data.Status;
import com.mitrian.lab.common.data.Worker;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

public class FilterByStatusCommand extends AbstractCommand {

    private final Dao<Worker> dao;

    public FilterByStatusCommand(Printer printer, CommandSource source, List<String> arguments, boolean inputElement, Dao<Worker> dao) {
        super(printer, source, arguments, inputElement);
        this.dao = dao;
    }

    @Override
    public boolean execute() throws CollectionException {
        for (Worker element: dao.filterByStatus(Status.valueOf(arguments.get(0)))){
            printer.print(element.toString());
        }
        return true;
    }
}
