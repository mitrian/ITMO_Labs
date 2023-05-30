package com.mitrian.common.network.model.request;

import com.mitrian.common.commands.AbstractCommand;

import java.io.Serializable;
import java.net.InetSocketAddress;

public final class CommandRequest extends Request implements Serializable {
	private final AbstractCommand command;

	public CommandRequest(InetSocketAddress from, InetSocketAddress to, AbstractCommand command) {
		super(from, to);
		this.command = command;
	}

	public AbstractCommand getCommand()
	{
		return command;
	}
}
