package com.mitrian.common.commands.resolver;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.cmdclass.ExecuteScriptCommand;
import com.mitrian.common.commands.factory.CommandFactory;
import com.mitrian.common.commands.file.WorkerFileReader;
import com.mitrian.common.commands.resolver.exception.ScriptResolvingException;
import com.mitrian.common.exceptions.IncorrectFieldException;
import com.mitrian.common.exceptions.ReaderException;
import com.mitrian.common.exceptions.impl.commands.CommandNotFoundException;

import java.io.BufferedReader;
import java.util.*;

public class CommandResolverImpl implements Resolver
{
	/**
     * Resolve commands from console
     * @param userInput line of user input
     * @return Command object for executing
     * @throws CommandNotFoundException unsupportable command
     */
    @Override
    public AbstractCommand resolve(String userInput) throws CommandNotFoundException
	{
        String[] localLine = userInput.trim().split(" ");
        List<String> args = Arrays.asList(
                Arrays.copyOfRange(localLine, 1, localLine.length)
        );

		AbstractCommand command = CommandFactory.newCommand(localLine[0], args);

		if (command instanceof ExecuteScriptCommand)
				command.setResolver(this);

        return command;
    }

	@Override
	public List<AbstractCommand> resolve(BufferedReader reader, List<String> parsedScript) throws ScriptResolvingException
	{
		List<AbstractCommand> commands = new ArrayList<>();
//		List<String> argsList = new ArrayList<>();

//		StringBuilder sb = new StringBuilder();
//		argsList.forEach((arg) -> sb.append(arg).append('\n'));
//		String elements = sb.toString();

		try
		{
			Scanner scanner = new Scanner(reader);
			while (scanner.hasNextLine())
			{
				String line = scanner.nextLine();
//				System.out.println("Got line: " + line);
				AbstractCommand command = resolve(line);


				if (command.getInputElement())
				{
					WorkerFileReader workerFileReader = new WorkerFileReader(scanner);
					command.setAdditionalArg(workerFileReader.createWorkerObject());
				}

				if (command instanceof ExecuteScriptCommand)
					command.setResolver(this);

				commands.add(command);

			}
		}
		catch (ReaderException | IncorrectFieldException e)
		{
			throw new ScriptResolvingException("Script is invalid. Check it and try again");
		}
		catch (CommandNotFoundException e)
		{
			throw new ScriptResolvingException("Fix the script. Found incorrect command");
		}
		catch (NoSuchElementException ignored)
		{
		}
		return commands;

	}
}
