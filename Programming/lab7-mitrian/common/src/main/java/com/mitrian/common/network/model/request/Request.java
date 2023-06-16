package com.mitrian.common.network.model.request;


import java.io.Serializable;
import java.net.InetSocketAddress;

/**
 * An abstract request entity
 *
 */
public abstract class Request implements Serializable {
	/**
	 * The address of sender
	 *
	 */
	private InetSocketAddress from;

	/**
	 * The destination address
	 *
	 */
	private InetSocketAddress to;

	/**
	 * Request constructor
	 *
	 * @param from source address
	 * @param to destination address
	 */
	public Request(InetSocketAddress from, InetSocketAddress to) {
		this.from = from;
		this.to = to;
	}

	/**
	 * Source address getter
	 *
	 * @return source address
	 */
	public InetSocketAddress getFrom()
	{
		return from;
	}

	/**
	 * Destination address getter
	 *
	 * @return destination address
	 */
	public InetSocketAddress getTo()
	{
		return to;
	}

	public void setFrom(InetSocketAddress from)
	{
		this.from = from;
	}

	public void setTo(InetSocketAddress to)
	{
		this.to = to;
	}
}
