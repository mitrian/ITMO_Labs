package com.mitrian.common.network.model.request;

import com.mitrian.common.auth.User;
import com.mitrian.common.commands.AbstractCommand;

import java.io.Serializable;
import java.net.InetSocketAddress;

public final class CommandRequest extends Request implements Serializable {
	private final AbstractCommand command;
	private final User user;

	public CommandRequest(InetSocketAddress from, InetSocketAddress to, AbstractCommand command, User user) {
		super(from, to);
		this.command = command;
		this.user = user;
	}

	public AbstractCommand getCommand()
	{
		return command;
	}
	public User getUser()
	{
		return user;
	}
}
