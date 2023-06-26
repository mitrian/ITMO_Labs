package com.mitrian.common.commands.cmdclass;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;
import com.mitrian.common.elements.Worker;
import com.mitrian.common.exceptions.CollectionException;
import com.mitrian.common.exceptions.DBCollectionException;
import com.mitrian.common.exceptions.impl.user.UserExistenceException;

import java.util.List;

/**
 * Command class to update element by input id
 */
public class UpdateCommand extends AbstractCommand {

    /**
     * Constructor for initialize fields
     * @param arguments param for initialize arguments field
     */
    public UpdateCommand(List<String> arguments) {
        super(1, arguments, true);
    }

    public UpdateCommand() {
    }

    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public ExecutionResult execute() throws UserExistenceException {
        try
        {
            dao.update(Integer.valueOf(arguments.get(0)), (Worker) additionalArg, user);
            return new ExecutionResult(ExecutionStatus.SUCCEED);
        }
        catch (DBCollectionException | CollectionException e)
        {
            return new ExecutionResult(ExecutionStatus.FAILED)
                    .append(e.getMessage());
        }
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "update";
    }
}
