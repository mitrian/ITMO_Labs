package com.mitrian.common.commands.cmdclass;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;
import com.mitrian.common.exceptions.CollectionException;

import java.util.List;

/**
 * Command class to print and remove first element of collection
 */
public class RemoveHeadCommand extends AbstractCommand {

    /**
     * Constructor for initialize fields
     * @param arguments param for initialize arguments field
     */
    public RemoveHeadCommand(List<String> arguments) {
        super(0, arguments, false);
    }

    public RemoveHeadCommand()
    {
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
            if (arguments.size() != 0){
                return new ExecutionResult(ExecutionStatus.FAILED)
                        .append("Введено неправильное количество аргументов");
            }

            return new ExecutionResult(ExecutionStatus.SUCCEED)
                    .append(dao.removeHead().toString());
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
        return "remove_head";
    }
}
