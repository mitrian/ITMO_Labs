package com.mitrian.lab.common.exetutors;

import com.mitrian.lab.common.commands.AbstractCommand;

public interface Executor {

    boolean execute(AbstractCommand command);
}
