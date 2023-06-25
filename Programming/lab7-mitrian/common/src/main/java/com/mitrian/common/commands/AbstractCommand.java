package com.mitrian.common.commands;

import com.mitrian.common.auth.User;
import com.mitrian.common.commands.resolver.Resolver;
import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.dao.Dao;
import com.mitrian.common.elements.Worker;
import com.mitrian.common.exceptions.DBCollectionException;
import com.mitrian.common.exceptions.UserException;
import com.mitrian.common.exceptions.impl.user.UserExistenceException;
import com.mitrian.common.executors.Executor;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.SQLException;
import java.util.List;

/**
 * Abstract command class
 */
public abstract class AbstractCommand implements Externalizable
{
    /** Current list of string arguments from ui */
    protected List<String> arguments;
    /** Current source field */
    protected int argAmount;
    /** Current input element field: true if we need to initialize new element, false if no */
    protected boolean inputElement;
    /** Contains worker object */
    protected Object additionalArg;
    /** Current dao of Worker class field */
    protected Dao<Worker> dao;
    /** Current name field */
    protected String name;
    /** Executor instance */
    protected Executor executor;
    protected Resolver resolver;
    protected User user;

    public AbstractCommand()
    {
    }

    /**
     * Constructor for initialize fields
     * @param argAmount param for initialize source field
     * @param arguments param for initialize arguments field
     * @param inputElement param for initialize input element field
     */
    public AbstractCommand(int argAmount, List<String> arguments, boolean inputElement){
        this.arguments = arguments;
        this.argAmount = argAmount;
        this.inputElement = inputElement;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(arguments);
        out.writeBoolean(inputElement);
        out.writeObject(additionalArg);
        out.writeObject(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        this.arguments = (List<String>) in.readObject();
        this.inputElement = in.readBoolean();
        this.additionalArg = in.readObject();
        this.name = (String) in.readObject();
    }

    /**
     * Executing command
     * @return execution result
     */
    public abstract ExecutionResult execute() throws ExecutionResult, DBCollectionException, SQLException, UserExistenceException;

    /**
     * Dao setter
     * @param dao param for initialize dao field
     */
    public void setDao(Dao<Worker> dao){
        this.dao = dao;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public void setResolver(Resolver resolver) {
        this.resolver = resolver;
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

    public List<String> getArguments()
    {
        return arguments;
    }

    public void setUser(User user){this.user = user;}

    /**
     * Getter of argAmount
     * @return argAmount
     */
    public int getArgAmount(){return argAmount;}

    /**
     * Getter of name
     * @return name
     */
    public String getNameOfCommand(){
        return name;
    }

    public Dao<Worker> getDao(){
        return dao;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
