package com.mitrian.client.ui;

import com.mitrian.client.ui.console.WorkerConsoleReader;
import com.mitrian.client.util.Printer;
import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.cmdclass.ExitCommand;
import com.mitrian.common.commands.cmdclass.SaveCommand;
import com.mitrian.common.commands.cmdclass.UpdateCommand;
import com.mitrian.common.commands.resolver.Resolver;
import com.mitrian.common.commands.util.parser.ArgumentParser;
import com.mitrian.common.exceptions.ForcedShutdownException;
import com.mitrian.common.network.UDPSocketClient;
import com.mitrian.common.network.exception.ServerNotAvailable;
import com.mitrian.common.network.model.request.CommandRequest;
import com.mitrian.common.network.model.request.IdRequest;
import com.mitrian.common.network.model.response.CommandResponse;
import com.mitrian.common.network.model.response.IdResponse;
import com.mitrian.common.network.model.response.ResponseCode;
import sun.misc.Signal;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * Class for running this app from console
 */
public class Console {

	/** Current resolver field */
	private final Resolver resolver;
	/** Current scanner field */
	private final Scanner scanner;
	/** Current printer field */
	private final Printer printer;
	private final UDPSocketClient client;

	private final InetSocketAddress SOURCE_ADDR;
	private final InetSocketAddress DESTINATION_ADDR; //= new InetSocketAddress(8080);


	/**
	 * Constructor for initialize fields
	 * @param printer param for initialize printer field
	 * @param scanner param for initialize scanner field
	 * @param resolver param for initialize resolver field
	 */
	public Console(
			Printer printer,
			Scanner scanner,
			Resolver resolver,
			InetSocketAddress sourceAddr,
			InetSocketAddress destAddr,
			UDPSocketClient client
	) {
		this.resolver = resolver;
		this.printer = printer;
		this.scanner = scanner;
		this.client = client;
		this.SOURCE_ADDR = sourceAddr;
		this.DESTINATION_ADDR = destAddr;
	}


	/**
	 * running app from console
	 */
	public void run() {

		Signal.handle(new Signal("INT"), (handler) -> {
			printer.println("Captured CTRL + C (SIGINT)");
			printer.println("Proceeding application interruption");
			System.exit(0);
		});

		while (true) {
			try {
				if (!scanner.hasNextLine()){
					throw new ForcedShutdownException("Не надо так");
				}

				String input = scanner.nextLine().trim();
				AbstractCommand command = resolver.resolve(input);

				if (command instanceof ExitCommand)
				{
					printer.println("Terminating current client");
					System.exit(0);
				}

				if (command instanceof SaveCommand)
				{
					printer.println("This command is not allowed from client side");
					continue;
				}

//				TODO: read server port on client startup

//				If update command discovered
				if (command instanceof UpdateCommand)
				{
					IdRequest checkIdRequest = new IdRequest(
							SOURCE_ADDR,
							DESTINATION_ADDR,
						ArgumentParser.parseInteger(command.getArguments().get(0))
					);

					IdResponse idResponse = client.sendRequestAndWaitResponse(checkIdRequest);
					if (idResponse.getCode() == ResponseCode.FAILED)
					{
						printer.println("No such id");
						continue;
					}

					try
					{
						WorkerConsoleReader workerConsoleReader = new WorkerConsoleReader(scanner, printer);
						command.setAdditionalArg(workerConsoleReader.createWorkerObject());
					}
					catch (ForcedShutdownException e)
					{
						printer.println("Element input interrupted");
						continue;
					}
				}

//				If non update command uses element from console
				if (command.getInputElement() && !(command instanceof UpdateCommand))
				{
					try
					{
						WorkerConsoleReader workerConsoleReader = new WorkerConsoleReader(scanner, printer);
						command.setAdditionalArg(workerConsoleReader.createWorkerObject());
					}
					catch (ForcedShutdownException e)
					{
						printer.println("Element input interrupted");
						continue;
					}
				}

				CommandRequest request = new CommandRequest(
						SOURCE_ADDR,
						DESTINATION_ADDR,
					command
				);

				CommandResponse response = client.sendRequestAndWaitResponse(request);
				printer.println(response.getMessage());
			}
			catch (ServerNotAvailable e)
			{
				printer.println(e.getMessage());
			}
			catch (Exception e) {
				printer.println(e.getMessage());
//				throw new RuntimeException(e);
			}
		}


	}

}