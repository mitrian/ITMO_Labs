package com.mitrian.common.commands.cmdclass;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;
import com.mitrian.common.commands.util.parser.ArgumentParser;
import com.mitrian.common.exceptions.CollectionException;
import com.mitrian.common.exceptions.IncorrectFieldException;

import java.util.List;

/**
 * Command class to remove collection element by id
 */
public class RemoveByIdCommand extends AbstractCommand {

    /**
     * Constructor for initialize fields
     * @param arguments param for initialize arguments field
     */
    public RemoveByIdCommand(List<String> arguments) {
        super(1, arguments, false);
    }

    public RemoveByIdCommand() {
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public ExecutionResult execute() {
        try {
            dao.remove(ArgumentParser.parseInteger(arguments.get(0)));
            return new ExecutionResult(ExecutionStatus.SUCCEED);
        }
        catch (IncorrectFieldException | CollectionException e) {
            return new ExecutionResult(ExecutionStatus.FAILED)
                    .append(e.getMessage());
        }
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "remove_by_id";
    }
}