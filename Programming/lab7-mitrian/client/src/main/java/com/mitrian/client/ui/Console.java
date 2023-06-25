package com.mitrian.client.ui;

import com.mitrian.client.ui.console.WorkerConsoleReader;
import com.mitrian.client.user.UserLoging;
import com.mitrian.client.util.Printer;
import com.mitrian.common.auth.User;
import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.cmdclass.ExitCommand;
import com.mitrian.common.commands.cmdclass.UpdateCommand;
import com.mitrian.common.commands.resolver.Resolver;
import com.mitrian.common.commands.util.parser.ArgumentParser;
import com.mitrian.common.elements.Worker;
import com.mitrian.common.exceptions.ForcedShutdownException;
import com.mitrian.common.network.UDPSocketClient;
import com.mitrian.common.network.exception.NetworkException;
import com.mitrian.common.network.exception.ServerNotAvailable;
import com.mitrian.common.network.model.request.CommandRequest;
import com.mitrian.common.network.model.request.IdRequest;
import com.mitrian.common.network.model.response.*;
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

	private final UserLoging userLoging;

	private User user;

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
			UDPSocketClient client,
			UserLoging userLoging
	) {
		this.resolver = resolver;
		this.printer = printer;
		this.scanner = scanner;
		this.client = client;
		this.SOURCE_ADDR = sourceAddr;
		this.DESTINATION_ADDR = destAddr;
		this.userLoging = userLoging;
	}


	/**
	 * running app from console
	 */
	public void run() throws ForcedShutdownException, NetworkException, ServerNotAvailable {

		Signal.handle(new Signal("INT"), (handler) -> {
			printer.println("Captured CTRL + C (SIGINT)");
			printer.println("Proceeding application interruption");
			System.exit(0);
		});

		boolean succesEnter = false;

		while (!succesEnter){
			printer.println("Для регистрации введите register, для входа login");
			String lline = scanner.nextLine().trim();
			if (lline.equals("login")){
				this.user = userLoging.readUserData();
				LoginResponse loginResponse = client.sendRequestAndWaitResponse(userLoging.login(user)) ;
				if (loginResponse.getCode() == ResponseCode.SUCCEED){
					printer.println("Вы успешно вошли в систему");
					succesEnter = true;
				} else {
					printer.println("Повторите попытку");
				}
			} else if (lline.equals("register")) {
				this.user = userLoging.readUserData();
				RegisterResponse registerResponse = client.sendRequestAndWaitResponse(userLoging.register(user));
				if (registerResponse.getCode() == ResponseCode.SUCCEED){
					printer.println("Вы успешно зарегистрировались");
					succesEnter = true;
				} else {
					printer.println("Повторите попытку");
				}
			}else {
				succesEnter = false;
			}
		}

		while (true) {
			try {

				if (!scanner.hasNextLine()){
					throw new ForcedShutdownException("Не надо так");
				}

				String input = scanner.nextLine().trim();
				AbstractCommand command = resolver.resolve(input);

				command.setUser(user);
				System.out.println("cl ok");
				if (command instanceof ExitCommand) {
					printer.println("Terminating current client");
					System.exit(0);
				}



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
						Worker worker = workerConsoleReader.createWorkerObject();
						worker.setUserName(user.getUserName());
						command.setAdditionalArg(worker);
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
					command,
						user
				);

				System.out.println("v console");
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
