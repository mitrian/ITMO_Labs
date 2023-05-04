package com.mitrian.lab.common;

import com.mitrian.lab.common.commands.AbstractCommand;

import java.io.Serializable;

public record Request(AbstractCommand command) implements Serializable {
}
