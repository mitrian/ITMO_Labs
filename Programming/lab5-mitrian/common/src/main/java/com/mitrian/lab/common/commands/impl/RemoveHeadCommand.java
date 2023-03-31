package com.mitrian.lab.common.commands.impl;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.utils.CommandSource;
import com.mitrian.lab.common.dao.Dao;
import com.mitrian.lab.common.data.Worker;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

public class RemoveHeadCommand extends AbstractCommand {
    private final Dao<Worker> dao;
    public RemoveHeadCommand(Printer printer, CommandSource source, List<String> arguments, boolean inputElement, Dao<Worker> dao) {
        super(printer, source, arguments, false);
        this.dao = dao;
    }

    @Override
    public boolean execute() throws CollectionException {
        printer.print(dao.removeHead().toString());
        return true;
    }
}
