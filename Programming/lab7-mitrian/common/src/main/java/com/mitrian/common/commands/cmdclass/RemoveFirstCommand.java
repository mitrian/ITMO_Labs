package com.mitrian.common.commands.cmdclass;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;
import com.mitrian.common.exceptions.CollectionException;
import com.mitrian.common.exceptions.DBCollectionException;

import java.sql.SQLException;
import java.util.List;

/**
 * Command class to remove first element from collection
 */
public class RemoveFirstCommand extends AbstractCommand {


    /**
     * Constructor for initialize fields
     * @param arguments param for initialize arguments field
     */
    public RemoveFirstCommand(List<String> arguments) {
        super(0, arguments, false);
    }

    public RemoveFirstCommand() {
    }

    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public ExecutionResult execute()
    {
        try
        {
            dao.removeFirst();
            return new ExecutionResult(ExecutionStatus.SUCCEED);
        }
        catch (CollectionException | DBCollectionException | SQLException e)
        {
            return new ExecutionResult(ExecutionStatus.FAILED)
                    .append(e.getMessage());
        }
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "remove_first";
    }
}
