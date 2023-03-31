package com.mitrian.lab.server.executors;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.exetutors.Executor;

public class SingleCommandExecutor implements Executor {



    @Override
    public boolean execute(AbstractCommand command) {
        return true;
    }
}
