package com.mitrian.lab.common.commands;

import com.mitrian.lab.common.commands.resolvers.Resolver;
import com.mitrian.lab.common.commands.utils.CommandSource;
import com.mitrian.lab.common.dao.Dao;
import com.mitrian.lab.common.data.Worker;
import com.mitrian.lab.common.exceptions.CollectionException;
import com.mitrian.lab.common.exetutors.Executor;
import com.mitrian.lab.common.utils.Printer;

import java.util.List;

/**
 * Abstract command class
 */
public abstract class AbstractCommand {
    /** Current printer field */
    protected Printer printer;
    /** Current list of string arguments from ui */
    protected final List<String> arguments;
    /** Current source field */
    protected final CommandSource source;
    /** Current input element field: true if we need to initialize new element, false if no */
    protected final boolean inputElement;
    /** Contains worker object */
    protected Object additionalArg;
    /** Current dao of Worker class field */
    protected Dao<Worker> dao;
    /** May contain script executor */
    protected Executor executor;
    /** Current resolver field */
    protected Resolver resolver;

    /**
     * Constructor for initialize fields
     * @param printer param for initialize printer field
     * @param source param for initialize source field
     * @param arguments param for initialize arguments field
     * @param inputElement param for initialize input element field
     */
    public AbstractCommand(Printer printer, CommandSource source, List<String> arguments, boolean inputElement){
        this.printer = printer;
        this.arguments = arguments;
        this.source = source;
        this.inputElement = inputElement;
    }

    /**
     * Executing command
     * @return status of executing
     * @throws CollectionException  exception with collection manipulation
     */
    public abstract boolean execute() throws CollectionException;

    /**
     * Dao setter
     * @param dao param for initialize dao field
     */
    public void setDao(Dao<Worker> dao){
        this.dao = dao;
    }

    /**
     * Resolver setter
     * @param resolver param for initialize resolver field
     */
    public void setResolver(Resolver resolver){
        this.resolver = resolver;
    }

    /**
     * Executor setter
     * @param executor param for initialize executor field
     */
    public void setExecutor(Executor executor){
        this.executor = executor;
    }

    /**
     * Additional arg setter
     * @param additionalArg param for initialize additional arg field
     */
    public void setAdditionalArg(Object additionalArg) {
        this.additionalArg = additionalArg;
    }

    /**
     * Input Element getter
     * @return input element
     */
    public boolean getInputElement()
    {
        return inputElement;
    }
}
