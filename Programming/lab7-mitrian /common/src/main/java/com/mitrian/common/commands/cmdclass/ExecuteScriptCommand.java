package com.mitrian.common.commands.cmdclass;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.resolver.exception.ScriptResolvingException;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;
import com.mitrian.common.commands.util.FileManager;
import com.mitrian.common.exceptions.FileException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Command Class for executing script
 */
public class ExecuteScriptCommand extends AbstractCommand {

    /**
     * Constructor for initialize fields
     * @param arguments param for initialize arguments field
     */
    public ExecuteScriptCommand(List<String> arguments){
        super(1, arguments, false);
    }

    public ExecuteScriptCommand() {
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public ExecutionResult execute()  {
        try
        {
            File script = FileManager.getFileByName(arguments.get(0));
            BufferedReader reader = new BufferedReader(new FileReader(script));
            BufferedReader testReader = new BufferedReader(new FileReader(script));
            List<AbstractCommand> commands = resolver.resolve(testReader, reader.lines().toList());

            for(AbstractCommand c : commands)
                c.setUser(user);
            
            return executor.execute(script.getName(), commands);
        }
        catch (FileException | ScriptResolvingException | FileNotFoundException e)
        {
            return new ExecutionResult(ExecutionStatus.FAILED)
                    .append(e.getMessage());
        }
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "execute_script";
    }
}