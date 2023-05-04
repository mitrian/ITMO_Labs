package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.elements.Person;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.utils.Printer;

import java.io.IOException;
import java.util.List;

/**
 * Command class for printing unique person fields of collection elements
 */
public class PrintUniquePersonCommand extends AbstractCommand {

    /**
     * Constructor for initialize fields
     * @param printer      param for initialize printer field
     * @param arguments    param for initialize arguments field
     */
    public PrintUniquePersonCommand(Printer printer, List<String> arguments) {
        super(printer, 0, arguments, false);
    }


    /**
     * Executing command
     * @return status of executing
     */
    @Override
    public boolean execute() throws CollectionException, IOException {
        for (Person person: dao.printUniquePerson()){
            printer.println(person.toString());
        }
        return true;
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "print_unique_person";
    }
}
