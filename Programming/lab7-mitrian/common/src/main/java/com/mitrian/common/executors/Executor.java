package com.mitrian.common.executors;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.exceptions.DBCollectionException;
import com.mitrian.common.exceptions.impl.user.UserExistenceException;

import java.sql.SQLException;
import java.util.List;

public interface Executor {

    /**
     * Executing single command
     * @param command for executing
     * @return executing status
     */
    default ExecutionResult execute(AbstractCommand command) throws DBCollectionException, SQLException, UserExistenceException {
        return null;
    }


    /**
     * Executing single command
     * @param lines with list of command for executing
     * @return executing status
     */
    default ExecutionResult execute(String fileName, List<AbstractCommand> lines) {
        return null;
    }
}
