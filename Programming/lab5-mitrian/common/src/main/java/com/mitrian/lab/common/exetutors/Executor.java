package com.mitrian.lab.common.exetutors;

import com.mitrian.lab.common.commands.AbstractCommand;

import java.util.List;

public interface Executor {

    /**
     * Executing single command
     * @param command for executing
     * @return executing status
     * @throws Exception exception depends in impl
     */
    default boolean execute(AbstractCommand command) throws Exception {
        return false;
        //TODO
    }


    /**
     * Executing single command
     * @param lines with list of command for executing
     * @return executing status
     * @throws Exception exception depends in impl
     */
    default boolean execute(List<AbstractCommand> lines) throws Exception {
        return false;
        //TODO
    }
}
