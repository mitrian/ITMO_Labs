package com.mitrian.lab.common.network.model.impl.command;

import com.mitrian.lab.common.network.model.Response;
import com.mitrian.lab.common.network.model.impl.util.ResponseStatus;

/**
 * This class represents a response abstraction
 */
public class CommandResponse implements Response {

	/**
	 * Response status
	 */
	private final ResponseStatus status;

	/**
	 * Response message
	 */
	private final String message;

	/**
	 * Command response constructor
	 *
	 * @param status response status
	 * @param message response message
	 */
	public CommandResponse(ResponseStatus status, String message)
	{
		this.status = status;
		this.message = message;
	}

	/**
	 * Response status getter
	 *
	 * @return response status
	 */
	public ResponseStatus getStatus() {
		return status;
	}

	/**
	 * Response message getter
	 *
	 * @return response getter
	 */
	public String getMessage() {
		return message;
	}
}
