package com.mitrian.lab.commands.managers;

import com.mitrian.lab.commands.*;
import com.mitrian.lab.utils.AbstractCommand;
import java.util.HashMap;
import java.util.logging.Filter;

public class CommandCollection {
    public static HashMap<String, AbstractCommand> commandCollection = new HashMap<>();
    public static HashMap<String, String> commandDescription = new HashMap<>();

    public CommandCollection(){
        commandCollection.put("help", new HelpCommand());
        commandDescription.put("help", new HelpCommand().getDescriptor());
        // commandCollection.put("info", new InfoCommand());
        commandCollection.put("show", new ShowCommand());
        commandDescription.put("show", new ShowCommand().getDescriptor());
        commandCollection.put("add", new AddCommand());
        commandDescription.put("add", new AddCommand().getDescriptor());
        //commandCollection.put("updateId", new HelpCommand()); //так можно???
       // commandDescription.put("updateId",new U)
        commandCollection.put("remove_by_id", new RemoveByIdCommand());
        commandDescription.put("remove_by_id",new RemoveByIdCommand().getDescriptor());
        commandCollection.put("clear", new ClearCommand());
        commandDescription.put("clear", new ClearCommand().getDescriptor());
        // commandCollection.put("save", new SaveCommand());
        // commandCollection.put("execute_script", new ExecuteScriptCommand());
        commandCollection.put("exit", new ExitCommand());
        commandDescription.put("exit", new ExitCommand().getDescriptor());
        commandCollection.put("remove_first", new RemoveFirstCommand());
        commandDescription.put("remove_first", new RemoveFirstCommand().getDescriptor());
        commandCollection.put("remove_head", new RemoveHeadCommand());
        commandDescription.put("remove_head", new RemoveHeadCommand().getDescriptor());
        // commandCollection.put("remove_greater", new RemoveGreaterCommand());
        //commandCollection.put("min_by_name", new MinByNameCommand());
       // commandCollection.put("filter_by_status", new FilterByStatusCommand());
      //  commandCollection.put("print_unique_person", new PrintUniquePerson());


    }
}
