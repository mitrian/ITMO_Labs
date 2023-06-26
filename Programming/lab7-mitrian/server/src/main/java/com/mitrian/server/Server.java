package com.mitrian.server;

import com.mitrian.common.commands.resolver.CommandResolverImpl;
import com.mitrian.common.commands.resolver.Resolver;
import com.mitrian.common.dao.Dao;
import com.mitrian.common.elements.Worker;
import com.mitrian.common.executors.Executor;
import com.mitrian.common.network.UDPChannelServer;
import com.mitrian.common.network.exception.NetworkException;
import com.mitrian.common.network.util.mapper.RequestMapper;
import com.mitrian.common.network.util.mapper.ResponseMapper;
import com.mitrian.server.collection.Collection;
import com.mitrian.server.collection.CollectionDBImpl;
import com.mitrian.server.dao.WorkerDaoImpl;
import com.mitrian.server.database.DBConnectionManagerImpl;
import com.mitrian.server.database.DatabaseManager;
import com.mitrian.server.executors.CommandExecutorImpl;
import com.mitrian.server.handler.DefaultRequestHandler;
import com.mitrian.server.tasks.ReceivingTask;
import com.mitrian.server.threads.ServerCLIThread;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Server
{
	public static final Logger LOGGER = Logger.getLogger(Server.class.getName());

	private static ExecutorService receivingFixedThreadPool;

	private static ExecutorService handlingFixedThreadPool;


	public static void main(String[] args)
	{

		//TODO exit
		handlingFixedThreadPool = Executors.newFixedThreadPool(4);
		receivingFixedThreadPool = Executors.newFixedThreadPool(1);



		RequestMapper requestMapper = new RequestMapper();
		ResponseMapper responseMapper = new ResponseMapper();


		Resolver resolver = new CommandResolverImpl();


		try
		{
			UDPChannelServer server = new UDPChannelServer(Integer.parseInt(args[0]), requestMapper, responseMapper);

			String url = "jdbc:postgresql://pg:5432/studs";
			String userName = System.getenv("USER");
			String password = System.getenv("PASSWORD");

			DBConnectionManagerImpl dbConnectionManager = new DBConnectionManagerImpl( url, userName, password);
			DatabaseManager databaseManager = new DatabaseManager(dbConnectionManager);

			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				try {
					dbConnectionManager.close();
				}
				catch (SQLException e) {
					LOGGER.warning(e.getMessage());
				}
			}));


			Collection<Worker> workerCollection = new CollectionDBImpl(dbConnectionManager, databaseManager);
			workerCollection.load();
			LOGGER.info("Коллекция успешно загружена");

			Dao<Worker> workerDao = new WorkerDaoImpl(workerCollection);
			Executor executor = new CommandExecutorImpl(workerDao);
			DefaultRequestHandler defaultRequestHandler = new DefaultRequestHandler(resolver, workerDao, executor);

			server.setRequestHandler(defaultRequestHandler);

			receivingFixedThreadPool.submit(new ReceivingTask(server, handlingFixedThreadPool));
			(new ServerCLIThread()).start();

		}
		catch (NetworkException e)
		{
			LOGGER.severe(e.getMessage());
			System.exit(0);
		}
		catch (Exception e) {
			//e.printStackTrace();
			LOGGER.warning(e.getMessage());
		}
	}
}

