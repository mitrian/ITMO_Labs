package com.mitrian.common.network.model.response;

import java.io.Serializable;
import java.net.InetSocketAddress;

/**
 * An abstract response entity
 *
 */
public abstract class Response implements Serializable {
	/**
	 * The address of sender
	 *
	 */
	private final InetSocketAddress from;

	/**
	 * The destination address
	 *
	 */
	private final InetSocketAddress to;

	/**
	 * Response code
	 */
	protected final ResponseCode code;

	/**
	 * Response constructor
	 *
	 * @param from source address
	 * @param to destination address
	 * @param code response code
	 */
	public Response(InetSocketAddress from, InetSocketAddress to, ResponseCode code) {
		this.from = from;
		this.to = to;
		this.code = code;
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

	/**
	 * Response status code getter
	 *
	 * @return response code
	 */
	public ResponseCode getCode()
	{
		return code;
	}
}
