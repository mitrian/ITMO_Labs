package com.mitrian.server.handler;

import com.mitrian.common.auth.User;
import com.mitrian.common.commands.AbstractCommand;
import com.mitrian.common.commands.resolver.Resolver;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;
import com.mitrian.common.dao.Dao;
import com.mitrian.common.exceptions.DBCollectionException;
import com.mitrian.common.executors.Executor;
import com.mitrian.common.network.model.handler.RequestHandler;
import com.mitrian.common.network.model.request.*;
import com.mitrian.common.network.model.response.*;
import com.mitrian.server.Server;

import java.sql.SQLException;
import java.util.logging.Logger;

public class DefaultRequestHandler implements RequestHandler {

    private final Logger logger;

    private final Resolver resolver;

    private final Executor executor;

    private final Dao workerDao;

    public DefaultRequestHandler( Resolver resolver, Dao dao, Executor executor){
        this.resolver = resolver;
        this.executor = executor;
        this.workerDao = dao;
        this.logger = Logger.getLogger(Server.class.getName());
    }

    @Override
    public AbstractResponse handle(Request request) {
        if (request instanceof CommandRequest req){
            logger.info("Proceeding command request");
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
            try {
                ExecutionResult executionResult = executor.execute(command);
                if (executionResult.getStatus() == ExecutionStatus.SUCCEED) {
                    return new CommandResponse(
                            req.getTo(),
                            req.getFrom(),
                            ResponseCode.SUCCEED,
                            executionResult
                    );
                }
                return new CommandResponse(req.getTo(), req.getFrom(), ResponseCode.FAILED, executionResult);
            } catch (ExecutionResult | DBCollectionException e){
                return new CommandResponse(
                        req.getTo(),
                        req.getFrom(),
                        ResponseCode.FAILED,
                        new ExecutionResult(ExecutionStatus.FAILED)
                            .append("Some troubles with execution")
                );
            }
        }

        if (request instanceof IdRequest req) {
            logger.info("Proceeding id request");
            Integer id = req.getId();

            if (workerDao.checkIdExists(id))
                return new IdResponse(req.getTo(), req.getFrom(), ResponseCode.SUCCEED);
            else
                return new IdResponse(req.getTo(), req.getFrom(), ResponseCode.FAILED);
        }

        if (request instanceof LoginRequest req){
            logger.info("Proceeding login request");
            User user = (req).getUser();
            try {
                if (workerDao.signIn(user)){
                    return new LoginResponse(req.getTo(), req.getFrom(),ResponseCode.SUCCEED);
                } else {
                    return new LoginResponse(req.getTo(), req.getFrom(),ResponseCode.FAILED);
                }
            } catch (SQLException e){
                return new LoginResponse(req.getTo(), req.getFrom(),ResponseCode.FAILED);
            }

        }

        if (request instanceof RegisterRequest req){
            logger.info("Proceeding register request");
            User user = (req).getUser();
            try {
                if (workerDao.signIn(user)){
                    return new LoginResponse(req.getTo(), req.getFrom(),ResponseCode.SUCCEED);
                } else {
                    return new RegisterResponse(req.getTo(), req.getFrom(),ResponseCode.FAILED);
                }
            } catch (SQLException e){
                return new RegisterResponse(req.getTo(), req.getFrom(),ResponseCode.FAILED);
            }
        }
        return null;
    }
}
