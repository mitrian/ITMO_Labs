package com.mitrian.server.executors;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.cmdclass.ExecuteScriptCommand;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;
import com.mitrian.common.dao.Dao;
import com.mitrian.common.elements.Worker;
import com.mitrian.common.executors.Executor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommandExecutorImpl implements Executor
{
	/** Current field of executing files */
    private static final Set<String> openFiles = new HashSet<>();
	private final Dao<Worker> workerDao;

    public CommandExecutorImpl(Dao<Worker> workerDao) {
        this.workerDao = workerDao;
    }

    @Override
    public ExecutionResult execute(AbstractCommand command)
    {
        command.setDao(workerDao);
        if (command instanceof ExecuteScriptCommand) {
            command.setExecutor(this);
        }
        return command.execute();
    }

	@Override
    public ExecutionResult execute(String fileName, List<AbstractCommand> commands)
	{
        if (openFiles.contains(fileName)){
            return new ExecutionResult(ExecutionStatus.FAILED)
                .append("Исполнение невозможно из-за возникновения рекурсии");
        }

        openFiles.add(fileName);

        ExecutionResult scriptResult = new ExecutionResult(ExecutionStatus.SUCCEED);

        for (AbstractCommand command : commands) {
            if (command instanceof ExecuteScriptCommand){
                command.setExecutor(this);
            }
            command.setDao(workerDao);

            try {
                ExecutionResult cmdResult = command.execute();
                scriptResult.append(cmdResult.getMessage());
            }
            catch (Exception e) {
                openFiles.remove(fileName);
                return new ExecutionResult(ExecutionStatus.FAILED)
                        .append("В исполнении скрипта возникла ошибка")
                        .append(e.getMessage());
            }

        }

        openFiles.remove(fileName);
        return scriptResult;
    }
}
