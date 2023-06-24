package com.mitrian.common.network.model.request;

import java.io.Serializable;
import java.net.InetSocketAddress;

public class IdRequest extends Request implements Serializable {
	private final Integer id;

	/**
	 * Request constructor
	 *
	 * @param from source address
	 * @param to   destination address
	 */
	public IdRequest(InetSocketAddress from, InetSocketAddress to, Integer id) {
		super(from, to);
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}
}
