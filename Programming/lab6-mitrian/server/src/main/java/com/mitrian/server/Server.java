package com.mitrian.server;

import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.resolver.CommandResolverImpl;
import com.mitrian.common.commands.resolver.Resolver;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;
import com.mitrian.common.commands.util.FileManager;
import com.mitrian.common.dao.Dao;
import com.mitrian.common.elements.Worker;
import com.mitrian.common.executors.Executor;
import com.mitrian.common.network.UDPChannelServer;
import com.mitrian.common.network.exception.NetworkException;
import com.mitrian.common.network.model.request.CommandRequest;
import com.mitrian.common.network.model.request.IdRequest;
import com.mitrian.common.network.model.response.CommandResponse;
import com.mitrian.common.network.model.response.IdResponse;
import com.mitrian.common.network.model.response.ResponseCode;
import com.mitrian.common.network.util.mapper.RequestMapper;
import com.mitrian.common.network.util.mapper.ResponseMapper;
import com.mitrian.server.collection.Collection;
import com.mitrian.server.collection.CollectionImpl;
import com.mitrian.server.dao.WorkerDaoImpl;
import com.mitrian.server.executors.CommandExecutorImpl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class Server
{
	public static final Logger LOGGER = Logger.getLogger(Server.class.getName());

	public static void main(String[] args)
	{
		if (args == null || args.length != 2) {
			LOGGER.severe("Для запуска укажите файл с коллекцией и порт сервера");
			System.exit(0);
		}

//		Init mappers
		RequestMapper requestMapper = new RequestMapper();
		ResponseMapper responseMapper = new ResponseMapper();

		Resolver resolver = new CommandResolverImpl();

		try(UDPChannelServer server = new UDPChannelServer(Integer.parseInt(args[1]), requestMapper, responseMapper))
		{
			Collection<Worker> workerCollection = new CollectionImpl(FileManager.getFileByName(args[0]));
			workerCollection.load();
			LOGGER.info("Коллекция успешно загружена");
			Dao<Worker> workerDao = new WorkerDaoImpl(workerCollection);

			Executor executor = new CommandExecutorImpl(workerDao);

			while (true)
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

				if (reader.ready())
				{
					String line = reader.readLine().strip();
					if ("save".equalsIgnoreCase(line))
						workerCollection.save();
				}

				server.listenAndHandleRequests((request) -> {

					if (request instanceof CommandRequest req)
					{
						LOGGER.info("Proceeding command request");
						AbstractCommand command = req.getCommand();

						if (command == null)
							return new CommandResponse(
									req.getTo(),
									req.getFrom(),
									ResponseCode.FAILED,
									new ExecutionResult(ExecutionStatus.FAILED)
											.append("Empty command got. Failed to execute")
							);

						command.setResolver(resolver);
						ExecutionResult executionResult = executor.execute(command);

						if (executionResult.getStatus() == ExecutionStatus.SUCCEED)
						{
							return new CommandResponse(
									req.getTo(),
									req.getFrom(),
									ResponseCode.SUCCEED,
									executionResult
							);
						}

						return new CommandResponse(req.getTo(), req.getFrom(), ResponseCode.FAILED, executionResult);
					}

					if (request instanceof IdRequest req)
					{
						LOGGER.info("Proceeding id request");
						Integer id = req.getId();

						if (workerDao.checkIdExists(id))
							return new IdResponse(req.getTo(), req.getFrom(), ResponseCode.SUCCEED);
						else
							return new IdResponse(req.getTo(), req.getFrom(), ResponseCode.FAILED);
					}

					return null;
				});
			}

		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			LOGGER.severe("Для запуска укажите файл");
			System.exit(0);
		}
		catch (NetworkException e)
		{
			LOGGER.severe(e.getMessage());
			System.exit(0);
		}
		catch (Exception e)
		{
			LOGGER.warning(e.getMessage());
		}
	}
}
