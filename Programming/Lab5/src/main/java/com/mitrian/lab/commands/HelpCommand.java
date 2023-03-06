package com.mitrian.lab.commands;

import com.mitrian.lab.utils.AbstractCommand;

public class HelpCommand extends AbstractCommand {
    private String descriptor = "вывести справку по доступным командам";

    @Override
    public boolean execute(String[] args) {
        return false;
    }

    @Override
    public boolean execute() {

        return false;
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }
}
