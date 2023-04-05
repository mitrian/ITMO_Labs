package com.mitrian.lab.server.executors;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.cmdclasses.ExecuteScriptCommand;
import com.mitrian.lab.common.dao.Dao;
import com.mitrian.lab.common.data.Worker;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.exetutors.Executor;

public class SingleCommandExecutor implements Executor {

    //dao
    //constr with dao
    private Dao<Worker> workerDao;

    public SingleCommandExecutor(Dao<Worker> workerDao){
        this.workerDao = workerDao;
    }

    @Override
    public boolean execute(AbstractCommand command) throws CollectionException {
        command.setDao(workerDao);
        if (command instanceof ExecuteScriptCommand){
            command.setExecutor(new FileCommandExecutor(workerDao));
        }
        command.execute();
        return true;
    }
}
