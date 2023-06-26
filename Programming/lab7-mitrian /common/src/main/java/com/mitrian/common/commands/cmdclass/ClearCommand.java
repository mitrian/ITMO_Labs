package com.mitrian.common.commands.cmdclass;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;
import com.mitrian.common.exceptions.DBCollectionException;
import com.mitrian.common.exceptions.impl.user.UserExistenceException;

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
    public ExecutionResult execute() throws DBCollectionException, UserExistenceException {
        dao.clear(user);
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