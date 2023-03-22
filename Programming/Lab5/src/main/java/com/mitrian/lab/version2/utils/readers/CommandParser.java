package com.mitrian.lab.version2.utils.readers;

import com.mitrian.lab.version2.commands.managers.CommandCollection;

public class CommandParser {
    private CommandCollection commands;

    public CommandParser(CommandCollection commands){
        this.commands = commands;
    }


    public String[] parseCommand(String scanner) {
        String[] localScanner = scanner.trim().split(" ");
        String command = localScanner[0];
        if (commands.getCommandCollection().containsKey(command)) {
            if (localScanner.length > 2) {
                return new String[]{"Количество аргументов у команды должно равняться одному"};
            } else {
                return localScanner;
            }
        } else {
            return new String[]{"Такой команды не существует"};
        }
    }
}
