package com.mitrian.lab.server.executors;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.cmdclasses.ExecuteScriptCommand;
import com.mitrian.lab.common.commands.utils.ExecutionResult;
import com.mitrian.lab.common.dao.Dao;
import com.mitrian.lab.common.elements.Worker;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.exceptions.impl.file.ScriptErrorException;
import com.mitrian.lab.common.exceptions.impl.file.ScriptRecursionException;
import com.mitrian.lab.common.exetutors.Executor;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileCommandExecutor implements Executor {


    /** Current field of executing files */
    private static final Set<String> openFiles = new HashSet<>();
    private final Dao<Worker> workerDao;

    public FileCommandExecutor(Dao<Worker> workerDao){this.workerDao = workerDao;}

    @Override
    public ExecutionResult execute(String fileName, List<AbstractCommand> commands) throws CollectionException, IOException, ScriptRecursionException, ScriptErrorException {
        ExecutionResult scriptExecutionResult = new ExecutionResult();

        if (openFiles.contains(fileName)){
            scriptExecutionResult.appendNewLine("Исполнение невозможно из-за возникновения рекурсии");
            return scriptExecutionResult;
//            throw new ScriptRecursionException("Исполнение невозможно из-за возникновения рекурсии");
        }
        openFiles.add(fileName);

        for (AbstractCommand command: commands){
            if (command instanceof ExecuteScriptCommand){
                command.setExecutor(this);
            }
            command.setDao(workerDao);
            try {
                command.execute();
                scriptExecutionResult.appendNewLine(command.getExecutionResult().toString());
            } catch (Exception e){
                openFiles.remove(fileName);
                scriptExecutionResult.appendNewLine("В исполнении скрипта возникла ошибка");
                throw new ScriptErrorException("В исполнении скрипта возникла ошибка");
            }

        }
        openFiles.remove(fileName);
        return scriptExecutionResult;
    }
}
