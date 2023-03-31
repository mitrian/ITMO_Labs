package com.mitrian.lab.common.commands.impl;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.utils.CommandSource;
import com.mitrian.lab.common.dao.Dao;
import com.mitrian.lab.common.data.Worker;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

public class AddCommand extends AbstractCommand {

    private final Dao<Worker> dao;

    public AddCommand(Printer printer, CommandSource source, List<String> arguments, Dao<Worker> dao) {
        super(printer, source, arguments, true);
        this.dao = dao;
    }

    @Override
    public boolean execute() {

        Worker worker = (Worker) additionalArg;
//        if worker exists
        dao.add(worker);
        return true;
    }
}
