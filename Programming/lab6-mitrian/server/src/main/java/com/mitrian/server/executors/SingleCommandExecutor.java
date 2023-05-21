//package com.mitrian.server.executors;
//
//import com.mitrian.common.commands.AbstractCommand;
//import com.mitrian.common.commands.cmdclass.ExecuteScriptCommand;
//import com.mitrian.common.dao.Dao;
//import com.mitrian.common.elements.Worker;
//import com.mitrian.common.exceptions.CollectionException;
//import com.mitrian.common.executors.Executor;
//
//import java.io.IOException;
//
//public class SingleCommandExecutor implements Executor {
//
//    //dao
//    //constr with dao
//    private final Dao<Worker> workerDao;
//
//    public SingleCommandExecutor(Dao<Worker> workerDao){
//        this.workerDao = workerDao;
//    }
//
//    @Override
//    public boolean execute(AbstractCommand command) throws CollectionException, IOException {
//        command.setDao(workerDao);
//        if (command instanceof ExecuteScriptCommand){
//            command.setExecutor(new FileCommandExecutor(workerDao));
//        }
//        command.execute();
//        return true;
//    }
//}
