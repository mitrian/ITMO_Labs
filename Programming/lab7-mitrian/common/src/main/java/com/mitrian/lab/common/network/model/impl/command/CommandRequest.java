package com.mitrian.lab.common.network.model.impl.command;

import com.mitrian.lab.common.commands.AbstractCommand;
import com.mitrian.lab.common.network.model.Request;

import java.net.InetSocketAddress;

/**
 * This class represents a request abstraction
 */
public class CommandRequest implements Request {
	/**
	 * Requested command instance
	 */
	private final AbstractCommand command;

	/**
	 * Request sender address
	 */
	private InetSocketAddress senderAddress;

	/**
	 * Command request constructor
	 *
	 * @param command requested command instance
	 */
	public CommandRequest(AbstractCommand command)
	{
		this.command = command;
	}

	/**
	 * Requested command instance getter
	 *
	 * @return command instance
	 */
	public AbstractCommand getCommand() {
		return command;
	}

	/**
	 * Requester address getter
	 *
	 * @return request sender address
	 */
	public InetSocketAddress getSenderAddress() {
		return senderAddress;
	}

	/**
	 * Requester address setter
	 */
	public void setSenderAddress(InetSocketAddress senderAddress) {
		this.senderAddress = senderAddress;
	}
}
