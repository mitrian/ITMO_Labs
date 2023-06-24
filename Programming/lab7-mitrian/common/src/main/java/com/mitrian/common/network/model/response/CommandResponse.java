package com.mitrian.common.network.model.response;

import com.mitrian.common.commands.util.ExecutionResult;
import com.mitrian.common.commands.util.ExecutionStatus;

import java.io.Serializable;
import java.net.InetSocketAddress;

public final class CommandResponse extends AbstractResponse implements Serializable {
	private final ExecutionResult result;

	public CommandResponse(
			InetSocketAddress from,
			InetSocketAddress to,
			ResponseCode code,
			ExecutionResult result
	) {
		super(from, to, code);
		this.result = result;
	}

	public String getMessage()
	{
		return result.getMessage();
	}

	public ExecutionStatus getStatus()
	{
		return result.getStatus();
	}

	public ExecutionResult getResult()
	{
		return result;
	}
}
