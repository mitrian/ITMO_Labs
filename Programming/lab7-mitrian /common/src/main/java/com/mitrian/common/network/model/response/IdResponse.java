package com.mitrian.common.network.model.response;

import java.io.Serializable;
import java.net.InetSocketAddress;

public class IdResponse extends AbstractResponse implements Serializable {
	/**
	 * Response constructor
	 *
	 * @param from source address
	 * @param to   destination address
	 * @param code response code
	 */
	public IdResponse(InetSocketAddress from, InetSocketAddress to, ResponseCode code)
	{
		super(from, to, code);
	}
}
