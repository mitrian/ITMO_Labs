package com.mitrian.lab.version2;

import com.mitrian.lab.version2.utils.AbstractCommand;
import com.mitrian.lab.version2.utils.exceptions.ForcedShutdownException;

public class CommandExecutor {
    private AbstractCommand command;

    public void setCommand(AbstractCommand command){
        this.command = command;
    }

    public boolean execute() throws ForcedShutdownException {
        return command.execute();
    }
}
