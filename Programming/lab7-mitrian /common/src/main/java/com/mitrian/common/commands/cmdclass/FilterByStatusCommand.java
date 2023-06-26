package com.mitrian.common.commands.cmdclass;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;
import com.mitrian.common.elements.Status;
import com.mitrian.common.elements.Worker;
import com.mitrian.common.exceptions.impl.user.UserExistenceException;

import java.sql.SQLException;
import java.util.List;

/**
 * Command class to filter collection elements by Status field
 */
public class FilterByStatusCommand extends AbstractCommand {


    /**
     * Constructor for initialize fields
     * @param arguments param for initialize arguments field
     */
    public FilterByStatusCommand(List<String> arguments) {
        super(1, arguments, false);
    }

    public FilterByStatusCommand() {
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public ExecutionResult execute() throws SQLException, UserExistenceException {
        ExecutionResult result = new ExecutionResult(ExecutionStatus.SUCCEED);
        for (Worker element: dao.filterByStatus(Status.valueOf(arguments.get(0)), user))
            result.append(element.toString());

        return result;
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "filter_by_id";
    }

}
