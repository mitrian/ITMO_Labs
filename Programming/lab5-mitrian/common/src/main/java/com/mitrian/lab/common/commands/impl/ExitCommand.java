package com.mitrian.lab.common.commands.impl;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.utils.CommandSource;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

public class ExitCommand extends AbstractCommand {
    public ExitCommand(Printer printer, CommandSource source, List<String> arguments, boolean inputElement) {
        super(printer, source, arguments, false);
    }

    @Override
    public boolean execute() {
        System.exit(1);
        printer.print("Завершение работы");
        return true;
    }
}
