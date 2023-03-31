package com.mitrian.lab.common.commands.impl;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.utils.CommandSource;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

public class HelpCommand extends AbstractCommand {

    public HelpCommand(Printer printer, CommandSource source, List<String> arguments, boolean inputElements) {
        super(printer, source, arguments, false);
    }

    @Override
    public boolean execute() {
//        some logic with CommandFactory
        return false;
    }
}
