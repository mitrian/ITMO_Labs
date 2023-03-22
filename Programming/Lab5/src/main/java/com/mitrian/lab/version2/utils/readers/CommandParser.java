package com.mitrian.lab.version2.utils.readers;

import com.mitrian.lab.version2.commands.managers.CommandCollection;

import java.util.stream.Stream;

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
                return Stream.concat(Stream.of(localScanner), Stream.of(new String[]{""})).toArray(String[]::new);
            }
        } else {
            return new String[]{"Такой команды не существует"};
        }
    }
}
