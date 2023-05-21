package com.mitrian.common.commands.cmdclass;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;

import java.util.List;

/**
 * Command class to exit from system
 */
public class ExitCommand extends AbstractCommand {

    /**
     * Constructor for initialize fields
     * @param arguments param for initialize arguments field
     */
    public ExitCommand(List<String> arguments) {
        super(0, arguments, false);
    }

    public ExitCommand()
    {
    }

    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public ExecutionResult execute() {
        System.exit(0);
        return new ExecutionResult(ExecutionStatus.SUCCEED);
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "exit";
    }
}
