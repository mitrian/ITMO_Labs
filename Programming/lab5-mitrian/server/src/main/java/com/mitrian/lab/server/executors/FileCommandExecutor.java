package com.mitrian.lab.server.executors;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.dao.Dao;
import com.mitrian.lab.common.data.Worker;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.exetutors.Executor;

import java.util.List;

public class FileCommandExecutor implements Executor {

    private final Dao<Worker> workerDao;

    public FileCommandExecutor(Dao<Worker> workerDao){this.workerDao = workerDao;}

    @Override
    public boolean execute(List<AbstractCommand> commands) throws CollectionException {
        for (AbstractCommand command: commands){
            command.setDao(workerDao);
            command.execute();
        }
        return true;
    }
}
