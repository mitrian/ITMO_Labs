package com.mitrian.lab.commands;

import com.mitrian.lab.commands.managers.CommandCollection;
import com.mitrian.lab.utils.AbstractCommand;
import com.mitrian.lab.utils.ConsolePrinter;
import com.mitrian.lab.utils.Printer;

import java.util.Iterator;
import java.util.Map;

public class HelpCommand extends AbstractCommand {
    private String descriptor = "вывести справку по доступным командам";
    Printer printer = new ConsolePrinter();

    @Override
    public boolean execute(String[] args) {
        printer.print("Не указывайте аргументы при использовании данной команды");
        return false;
    }

    @Override
    public boolean execute() {
        try {
//            Iterator it = CommandCollection.commandDescription.entrySet().iterator();
//            while (it.hasNext()){
//                Map.Entry pair = (Map.Entry)it.next();
//                System.out.println(pair.getKey()+" -> "+pair.getValue());
//            }
//            System.out.println("fwfw");
            for (Map.Entry<String, String> entry: CommandCollection.commandDescription.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                System.out.println(key+" --- "+value);
            }
            return true;
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
