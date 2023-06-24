package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.utils.Printer;

import java.io.IOException;
import java.io.InputStream;
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
    public boolean execute() {
        ClassLoader classLoader = HelpCommand.class.getClassLoader();
        try(InputStream ris = classLoader.getResourceAsStream("Help.txt")) {

            byte[] helpBytes = ris.readAllBytes();
            String help = new String(helpBytes);
            executionResult.appendNewLine(help);
            return true;
        }
        catch (IOException | NullPointerException e) {
            executionResult.appendNewLine("Unable to access help resource");
            return false;
        }
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "help";
    }
}
