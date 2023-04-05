package com.mitrian.lab.common.exetutors;

import com.mitrian.lab.common.commands.AbstractCommand;

import java.util.List;

public interface Executor {

    default boolean execute(AbstractCommand command) throws Exception {
        return false;
        //TODO
    }

    default boolean execute(List<AbstractCommand> lines) throws Exception {
        return false;
        //TODO
    }
}
