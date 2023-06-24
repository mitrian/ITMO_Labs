package com.mitrian.lab.server.executors;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.cmdclasses.ExecuteScriptCommand;
import com.mitrian.lab.common.commands.utils.ExecutionResult;
import com.mitrian.lab.common.dao.Dao;
import com.mitrian.lab.common.elements.Worker;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.exetutors.Executor;

public class SingleCommandExecutor implements Executor {

    //dao
    //constr with dao
    private final Dao<Worker> workerDao;

    public SingleCommandExecutor(Dao<Worker> workerDao){
        this.workerDao = workerDao;
    }

    @Override
    public ExecutionResult execute(AbstractCommand command) {

        command.setDao(workerDao);
        if (command instanceof ExecuteScriptCommand){
            command.setExecutor(new FileCommandExecutor(workerDao));
        }

        try {
            command.execute();
            return command.getExecutionResult();
        }
        catch (CollectionException e)
        {
            ExecutionResult executionResult = new ExecutionResult();
            executionResult.appendNewLine(e.getMessage());
            return executionResult;
        }
    }
}
