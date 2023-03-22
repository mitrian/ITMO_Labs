package com.mitrian.lab.version1.commands;

import com.mitrian.lab.version1.commands.managers.CommandCollection;
import com.mitrian.lab.version1.utils.AbstractCommand;
import com.mitrian.lab.version1.utils.Printer;

import java.util.Map;

public class HelpCommand extends AbstractCommand {
    private String descriptor = "вывести справку по доступным командам";
    private Printer printer;
    private String arguments;

    public HelpCommand(String arguments, Printer printer){
        this.arguments = arguments;
        this.printer = printer;
    }


    @Override
    public boolean execute() {
        try {
            if ("".equals(arguments)){
                for (Map.Entry<String, String> entry: CommandCollection.commandDescription.entrySet()){
                    String key = entry.getKey();
                    String value = entry.getValue();
                    System.out.println(key+" --- "+value);
                }
                return true;
            } else {
                printer.print("Не указывайте аргументы при использовании данной команды");
                return false;
            }

        } catch (Exception var5) {
            return false;
        }
    }



//        printer.print("true");
//        while (CommandCollection.commandCollection.entrySet().iterator().hasNext()){
//            printer.print(CommandCollection.commandCollection.entrySet().iterator().next().getKey());
//        }


    @Override
    public String getDescriptor() {
        return descriptor;
    }
}
