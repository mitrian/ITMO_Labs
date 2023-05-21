//package com.mitrian.server.executors;
//
//import com.mitrian.common.commands.AbstractCommand;
//import com.mitrian.common.commands.cmdclass.ExecuteScriptCommand;
//import com.mitrian.common.dao.Dao;
//import com.mitrian.common.elements.Worker;
//import com.mitrian.common.exceptions.CollectionException;
//import com.mitrian.common.exceptions.impl.file.ScriptErrorException;
//import com.mitrian.common.exceptions.impl.file.ScriptRecursionException;
//import com.mitrian.common.executors.Executor;
//
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class FileCommandExecutor implements Executor {
//
//    /** Current field of executing files */
//    private static final Set<String> openFiles = new HashSet<>();
//    private final Dao<Worker> workerDao;
//
//    public FileCommandExecutor(Dao<Worker> workerDao){this.workerDao = workerDao;}
//
//    @Override
//    public boolean execute(String fileName, List<AbstractCommand> commands) throws CollectionException, IOException, ScriptRecursionException, ScriptErrorException {
//        if (openFiles.contains(fileName)){
//            throw new ScriptRecursionException("Исполнение невозможно из-за возникновения рекурсии");
//        }
//        openFiles.add(fileName);
//
//        for (AbstractCommand command: commands){
//            if (command instanceof ExecuteScriptCommand){
//                command.setExecutor(this);
//            }
//            command.setDao(workerDao);
//            try {
//                command.execute();
//            } catch (Exception e){
//                openFiles.remove(fileName);
//                throw new ScriptErrorException("В исполнении скрипта возникла ошибка");
//            }
//
//        }
//        openFiles.remove(fileName);
//        return true;
//    }
//}
