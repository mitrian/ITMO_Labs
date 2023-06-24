package com.mitrian.lab.common.exetutors;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.utils.ExecutionResult;

import java.io.Serializable;
import java.util.List;

public interface Executor extends Serializable {

    /**
     * Executing single command
     * @param command for executing
     * @return executing status
     * @throws Exception exception depends in impl
     */
    default ExecutionResult execute(AbstractCommand command) throws Exception {
        return null;
    }


    /**
     * Executing single command
     * @param lines with list of command for executing
     * @return executing status
     * @throws Exception exception depends in impl
     */
    default ExecutionResult execute(String fileName, List<AbstractCommand> lines) throws Exception {
        return null;
    }
}
