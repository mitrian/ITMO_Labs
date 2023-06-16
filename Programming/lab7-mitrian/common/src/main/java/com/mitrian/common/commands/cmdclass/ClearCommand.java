package com.mitrian.common.commands.cmdclass;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;

import java.util.List;

/**
 * Command class to clear collection
 */
public class ClearCommand extends AbstractCommand {


    /**
     * Constructor for initialize fields
     * @param arguments param for initialize arguments field
     */
    public ClearCommand(List<String> arguments) {
        super(0, arguments, false);
    }

    public ClearCommand() {
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public ExecutionResult execute()  {
        dao.clear();
        return new ExecutionResult(ExecutionStatus.SUCCEED)
                .append("Collection cleared");
    }




    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "clear";
    }

}
