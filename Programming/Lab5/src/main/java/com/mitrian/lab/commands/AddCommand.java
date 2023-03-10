package com.mitrian.lab.commands;

import com.mitrian.lab.commands.managers.Initializer;
import com.mitrian.lab.utils.AbstractCommand;
import com.mitrian.lab.utils.exceptions.ForcedShutdownException;

import java.util.Arrays;

public class AddCommand extends AbstractCommand {
    public String getDescriptor;
    private String descriptor = "добавить новый элемент в коллекцию";

    @Override
    public boolean execute(String[] arguments) {
        try {
            String[] local = arguments;
            if (arguments.length > 10) {
                arguments = new String[10];
                arguments = (String[])Arrays.copyOfRange(local, 0, arguments.length);
            }

            if (arguments.length < 10) {
                arguments = new String[10];
                Arrays.fill(arguments, "");

                for(int i = 0; i < local.length; ++i) {
                    arguments[i] = local[i];
                }
            }

            Initializer.createWorkerObject();
            return true;
        } catch (ForcedShutdownException var4) {
            System.exit(1);
            return true;
        } catch (Exception var5) {
            return false;
        }
    }

    @Override
    public boolean execute() {
        try {
            String[] local = new String[10];
            Arrays.fill(local, "");
            return this.execute(local);
        } catch (Exception var2) {
            return false;
        }
    }

    @Override
    public String getDescriptor() {
        return descriptor;
    }
}

