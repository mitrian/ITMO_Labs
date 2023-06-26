package com.mitrian.common.commands.cmdclass;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;
import com.mitrian.common.elements.Worker;
import com.mitrian.common.exceptions.DBCollectionException;
import com.mitrian.common.exceptions.impl.user.UserExistenceException;

import java.util.List;

/**
 * Command class to remove collection element, witch is bigger than input element
 */
public class RemoveGreaterCommand extends AbstractCommand {

    /**
     * Constructor for initialize fields
     * @param arguments param for initialize arguments field
     */
    public RemoveGreaterCommand(List<String> arguments) {
        super(0, arguments, true);
    }

    public RemoveGreaterCommand() {
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public ExecutionResult execute() throws DBCollectionException, UserExistenceException {
        Worker worker = (Worker) additionalArg;
        dao.removeGreater(worker, user);
        return new ExecutionResult(ExecutionStatus.SUCCEED);
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "remove_greater";
    }
}
