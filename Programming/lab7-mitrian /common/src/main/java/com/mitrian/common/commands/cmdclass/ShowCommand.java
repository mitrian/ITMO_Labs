package com.mitrian.common.commands.cmdclass;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;
import com.mitrian.common.exceptions.impl.user.UserExistenceException;

import java.sql.SQLException;
import java.util.List;

/**
 * Command class to show all elements in collection
 */
public class ShowCommand extends AbstractCommand {


    /**
     * Constructor for initialize fields
     * @param arguments param for initialize arguments field
     */
    public ShowCommand(List<String> arguments) {
        super(0, arguments, false);
    }

    public ShowCommand() {
    }

    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public ExecutionResult execute() throws SQLException, UserExistenceException {
        return new ExecutionResult(ExecutionStatus.SUCCEED)
                .append(dao.getAllElements(user).toString());
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "show";
    }
}
