package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

/**
 * Command class to see information about collection
 */
public class InfoCommand extends AbstractCommand {




    /**
     * Constructor for initialize fields
     * @param printer      param for initialize printer field
     * @param arguments    param for initialize arguments field
     */
    public InfoCommand(Printer printer, List<String> arguments) {
        super(printer, 0, arguments, false);
    }

    @Override
    public boolean execute() throws CollectionException {
        executionResult.appendNewLine("Тип: LinkedList collection");
        executionResult.appendNewLine("Дата инициализации: " + dao.getCreationDate());
        executionResult.appendNewLine("Количество элементов: " + dao.getSize());
        return true;
    }


    /**
     * Getter name of command
     */
    public String getNameOfCommand(){
        return "info";
    }
}
