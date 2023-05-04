package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.utils.Printer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Command class to see possible commands and their meaning
 */
public class HelpCommand extends AbstractCommand {


    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param arguments param for initialize arguments field
     */
    public HelpCommand(Printer printer, List<String> arguments) {
        super(printer, 0, arguments, false);
    }

    @Override
    public boolean execute() throws IOException {
        byte[] helpBytes = HelpCommand.class.getClassLoader().getResourceAsStream("Help.txt").readAllBytes();
        String help = new String(helpBytes);
        printer.println(help);
        return true;
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "help";
    }
}
