package com.mitrian.client;

import com.mitrian.client.ui.Console;
import com.mitrian.client.util.ConsolePrinter;
import com.mitrian.client.util.Printer;
import com.mitrian.common.commands.resolver.CommandResolverImpl;
import com.mitrian.common.commands.resolver.Resolver;
import com.mitrian.common.network.UDPSocketClient;
import com.mitrian.common.network.exception.NetworkException;
import com.mitrian.common.network.util.mapper.RequestMapper;
import com.mitrian.common.network.util.mapper.ResponseMapper;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * Main class
 */
public class Client
{

	public static void main(String[] args)
	{
		Printer printer = new ConsolePrinter();
		if (args == null || args.length != 2) {
			printer.println("Для запуска укажите порт клиента и сервера");
			System.exit(0);
		}
		Scanner scanner = new Scanner(System.in);

		RequestMapper requestMapper = new RequestMapper();
		ResponseMapper responseMapper = new ResponseMapper();

		Resolver resolver = new CommandResolverImpl();


		try(UDPSocketClient client = new UDPSocketClient(Integer.parseInt(args[0]), Integer.parseInt(args[1]), requestMapper, responseMapper))
		{
			Console console = new Console(
					printer,
					scanner,
					resolver,
					new InetSocketAddress(Integer.parseInt(args[0])),
					new InetSocketAddress(Integer.parseInt(args[1])),
					client
			);
			console.run();
		}
		catch (NetworkException e)
		{
			printer.println(e.getMessage());
		}

	}
}
