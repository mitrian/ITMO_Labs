package com.mitrian.common.commands.cmdclass;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;
import com.mitrian.common.elements.Worker;
import com.mitrian.common.exceptions.DBCollectionException;
import com.mitrian.common.exceptions.impl.user.UserExistenceException;

import java.sql.SQLException;
import java.util.List;

/**
 * Command class for adding element into collection
 */
public class AddCommand extends AbstractCommand {

    /**
     * Constructor for initialize fields
     * @param arguments param for initialize arguments field
     */
    public AddCommand(List<String> arguments) {
        super(0, arguments, true);
    }

    public AddCommand() {
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public ExecutionResult execute() throws DBCollectionException, SQLException, UserExistenceException {
            Worker worker = (Worker) additionalArg;

            if (worker == null)
                return new ExecutionResult(ExecutionStatus.FAILED)
                        .append("Element was not set");

            dao.add(worker);
            return new ExecutionResult(ExecutionStatus.SUCCEED)
                    .append("Successfully added new worker");
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "add";
    }

}
