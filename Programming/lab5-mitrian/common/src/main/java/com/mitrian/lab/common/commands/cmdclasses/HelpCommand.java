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

    /** Current name of command field */
    private String name = "help";


    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param arguments param for initialize arguments field
     */
    public HelpCommand(Printer printer, List<String> arguments) {
        super(printer, 0, arguments, false);
    }
// TODO
    @Override
    public boolean execute() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(HelpCommand.class.getClassLoader().getResource("Help.txt").getFile())
        )){
            for (String line: reader.lines().toList()){
                printer.println(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }


    /**
     * Getter of name field
     */
    public String getNameOfCommand(){
        return name;
    }
}
//TODO
