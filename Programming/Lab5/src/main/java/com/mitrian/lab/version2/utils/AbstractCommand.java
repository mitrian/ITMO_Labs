package com.mitrian.lab.version2.utils;

import com.mitrian.lab.version2.utils.exceptions.ForcedShutdownException;

public abstract class AbstractCommand {

    public abstract boolean execute() throws ForcedShutdownException;

    public abstract String getDescriptor();

    public abstract String getName();

}
