package com.mitrian.common.commands.cmdclass;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;

import java.util.List;

/**
 * Command class to see information about collection
 */
public class InfoCommand extends AbstractCommand {

    /**
     * Constructor for initialize fields
     * @param arguments    param for initialize arguments field
     */
    public InfoCommand(List<String> arguments) {
        super(0, arguments, false);
    }

    public InfoCommand()
    {
    }

    @Override
    public ExecutionResult execute()
    {
        return new ExecutionResult(ExecutionStatus.SUCCEED)
                .append("Тип: LinkedList collection")
                .append("Дата инициализации: " + dao.getCreationDate())
                .append("Количество элементов: " + dao.getSize());
    }

    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "info";
    }
}
