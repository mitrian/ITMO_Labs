package com.mitrian.lab.client.resolvers;

import com.mitrian.lab.common.commands.AbstractCommand;

import java.io.File;
import java.util.List;

public interface Resolver {

    default AbstractCommand resolve(String userInput) {
        throw new UnsupportedOperationException(""); // TODO: implement throwing an exception: trying to use default method
    }

    default List<AbstractCommand> resolve(File file) {
        throw new UnsupportedOperationException(""); // TODO: implement throwing an exception: trying to use default method
    }
}
