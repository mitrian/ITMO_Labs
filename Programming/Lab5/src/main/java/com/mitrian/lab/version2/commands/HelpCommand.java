package com.mitrian.lab.version2.commands;

import com.mitrian.lab.version2.utils.readers.ConsoleCommandReader;
import com.mitrian.lab.version2.Receiver;
import com.mitrian.lab.version2.utils.AbstractCommand;
import com.mitrian.lab.version2.utils.Printer;

public class HelpCommand extends AbstractCommand {
    private String descriptor = "вывести справку по доступным командам";
    private Printer printer;
    private ConsoleCommandReader commandReader;

    private Receiver receiver;

    private String name = "help";

    public HelpCommand(Receiver receiver, ConsoleCommandReader commandReader, Printer printer){
        this.receiver = receiver;
        this.commandReader = commandReader;
        this.printer = printer;
    }


    @Override
    public boolean execute() {
        try {
            if ("".equals(commandReader.readLine()[0])){
//                for (Map.Entry<String, String> entry: CommandCollection.commandDescription.entrySet()){
//                    String key = entry.getKey();
//                    String value = entry.getValue();
//                    System.out.println(key+" --- "+value);
//                }
                printer.print(receiver.help());
                return true;
            } else {
                printer.print("Не указывайте аргументы при использовании данной команды");
                return false;
            }

        } catch (Exception var5) {
            return false;
        }
    }


    @Override
    public String getDescriptor() {
        return descriptor;
    }

    @Override
    public String getName() {return name;}
}
