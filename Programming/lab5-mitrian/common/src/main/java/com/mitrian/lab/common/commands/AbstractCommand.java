package com.mitrian.lab.common.commands;

import com.mitrian.lab.common.commands.utils.CommandSource;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

public abstract class AbstractCommand {

    protected Printer printer;
    /**
     * List of string arguments from cli : should be validated & used / ignored
     */
    protected List<String> arguments;
    protected CommandSource source;
    protected boolean inputElement;
    /**
     * Contains worker object
     */
    protected Object additionalArg;

    public AbstractCommand(Printer printer, CommandSource source, List<String> arguments, boolean inputElement){
        this.printer = printer;
        this.arguments = arguments;
        this.source = source;
        this.inputElement = inputElement;
    }

    public abstract boolean execute() throws CollectionException;

    public void setAdditionalArg(Object additionalArg) {
        this.additionalArg = additionalArg;
    }

    public boolean getInputElement()
    {
        return inputElement;
    }
}
