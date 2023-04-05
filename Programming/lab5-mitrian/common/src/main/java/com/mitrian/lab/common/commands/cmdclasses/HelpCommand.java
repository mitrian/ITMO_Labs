package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.utils.CommandSource;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

/**
 * Command class to see possible commands and their meaning
 */
public class HelpCommand extends AbstractCommand {

    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param source param for initialize source field
     * @param arguments param for initialize arguments field
     */
    public HelpCommand(Printer printer, CommandSource source, List<String> arguments) {
        super(printer, source, arguments, false);
    }
// TODO
    @Override
    public boolean execute() {
//        some logic with CommandFactory
        return false;
    }
}
