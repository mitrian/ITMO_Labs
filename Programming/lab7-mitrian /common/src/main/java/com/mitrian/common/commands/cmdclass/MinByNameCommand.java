package com.mitrian.common.commands.cmdclass;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;
import com.mitrian.common.exceptions.CollectionException;
import com.mitrian.common.exceptions.impl.user.UserExistenceException;

import java.sql.SQLException;
import java.util.List;

/**
 * Command class for outputting collection element with minimal size of name field
 */
public class MinByNameCommand extends AbstractCommand {

    /**
     * Constructor for initialize fields
     * @param arguments param for initialize arguments field
     */
    public MinByNameCommand(List<String> arguments) {
        super(0, arguments, false);
    }

    public MinByNameCommand() {
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public ExecutionResult execute() throws SQLException, UserExistenceException {
        try
        {
            return new ExecutionResult(ExecutionStatus.SUCCEED)
                    .append(dao.getMinByName(user).toString());
        }
        catch (CollectionException e)
        {
            return new ExecutionResult(ExecutionStatus.FAILED)
                    .append(e.getMessage());
        }
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "min_by_id";
    }
}
