package com.mitrian.common.commands.cmdclass;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;
import com.mitrian.common.elements.Person;

import java.util.List;

/**
 * Command class for printing unique person fields of collection elements
 */
public class PrintUniquePersonCommand extends AbstractCommand {

    /**
     * Constructor for initialize fields
     * @param arguments    param for initialize arguments field
     */
    public PrintUniquePersonCommand(List<String> arguments) {
        super(0, arguments, false);
    }

    public PrintUniquePersonCommand()
    {
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public ExecutionResult execute() {

        ExecutionResult result = new ExecutionResult(ExecutionStatus.SUCCEED);

        for (Person person: dao.printUniquePerson())
            result.append(person.toString());

        return result;
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "print_unique_person";
    }
}
