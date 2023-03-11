package com.mitrian.lab.utils;

public abstract class AbstractCommand {

    public abstract boolean execute(String args);

    public abstract boolean execute();

    public abstract String getDescriptor();

}
