package com.mitrian.common.commands.cmdclass;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Command class to see possible commands and their meaning
 */
public class HelpCommand extends AbstractCommand {


    /**
     * Constructor for initialize fields
     * @param arguments param for initialize arguments field
     */
    public HelpCommand(List<String> arguments) {
        super(0, arguments, false);
    }

    public HelpCommand()
    {
    }

    @Override
    public ExecutionResult execute() {
        ClassLoader classLoader = HelpCommand.class.getClassLoader();
        try(InputStream helpStream = classLoader.getResourceAsStream("Help.txt"))
        {
            if (helpStream == null)
            {
                return new ExecutionResult(ExecutionStatus.FAILED).append("Failed to open help");
            }

            return new ExecutionResult(ExecutionStatus.SUCCEED)
                    .append(new String(helpStream.readAllBytes()));
        }
        catch (IOException | NullPointerException e)
        {
            return new ExecutionResult(ExecutionStatus.FAILED)
                    .append("Failed to read help")
                    .append(e.getMessage());
        }
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "help";
    }
}
