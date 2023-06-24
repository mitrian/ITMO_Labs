package com.mitrian.lab.server;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.cmdclasses.UpdateCommand;
import com.mitrian.lab.common.commands.utils.ExecutionResult;
import com.mitrian.lab.common.commands.utils.FileManager;
import com.mitrian.lab.common.dao.Dao;
import com.mitrian.lab.common.elements.Worker;
import com.mitrian.lab.common.elements.initializer.IdCollection;
import com.mitrian.lab.common.exetutors.Executor;
import com.mitrian.lab.common.network.UDPServer;
import com.mitrian.lab.common.network.model.impl.command.CommandResponse;
import com.mitrian.lab.common.network.model.impl.util.ResponseStatus;
import com.mitrian.lab.server.collection.Collection;
import com.mitrian.lab.server.collection.CollectionImpl;
import com.mitrian.lab.server.dao.WorkerDaoImpl;
import com.mitrian.lab.server.executors.SingleCommandExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Server {

	public static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

	public static void main(String[] args) {


		if (args == null) {
			LOGGER.error("Please, specify a collection file to start the application");
			System.exit(0);
		}

		try (UDPServer server = new UDPServer()) {

			Collection<Worker> workerCollection = new CollectionImpl(FileManager.getFileByName(args[0]));
			Dao<Worker> workerDao = new WorkerDaoImpl(workerCollection);
			Executor executor = new SingleCommandExecutor(workerDao);
			workerCollection.load();

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			while (true) {

				if (br.ready() && "save".equals(br.readLine().trim())) {
					LOGGER.info("Saving collection...");
					workerDao.save();
					LOGGER.info("Collection saved");
				}

				server.run((request) -> {
				LOGGER.info("Got new request: '" + request.getCommand().getNameOfCommand() + "' command");

				try {

					AbstractCommand command = request.getCommand();

					if (command instanceof UpdateCommand upd) {
						Worker worker = (Worker) upd.getAdditionalArg();
						if (IdCollection.idCollection.contains(worker.getId())) {
							return new CommandResponse(ResponseStatus.FAILED, "Id already exists");
						}
					}

					ExecutionResult result = executor.execute(command);
					return new CommandResponse(ResponseStatus.SUCCESS, result.toString());
				}
				catch (Exception e) {
					return new CommandResponse(ResponseStatus.FAILED, e.getMessage());
				}
			});
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			LOGGER.error("Для запуска укажите файл");
			System.exit(0);
		}
		catch (Exception e)
		{
			LOGGER.error(e.getMessage(), e);
		}
	}
}
