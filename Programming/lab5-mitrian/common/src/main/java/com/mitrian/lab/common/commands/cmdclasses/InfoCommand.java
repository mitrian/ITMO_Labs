package com.mitrian.lab.common.commands.cmdclasses;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.commands.utils.CommandSource;
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
     * @param source       param for initialize source field
     * @param arguments    param for initialize arguments field
     */
    public InfoCommand(Printer printer, CommandSource source, List<String> arguments) {
        super(printer, source, arguments, false);
    }

    @Override
    public boolean execute() throws CollectionException {
        printer.println("Тип: LinkedList collection;"+ "\n"+
                "Дата инициализации: " + dao.getCreationDate()+ ";\n" +
                "Количество элементов: " + dao.getSize());
        return true;
    }
}
