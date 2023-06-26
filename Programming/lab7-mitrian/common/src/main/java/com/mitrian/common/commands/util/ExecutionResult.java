package com.mitrian.common.commands.util;

import java.io.Serializable;

public class ExecutionResult implements Serializable
{
	transient private final StringBuilder sb;

	private final ExecutionStatus status;
	private String message;

	public ExecutionResult(ExecutionStatus status)
	{
		this(status, "");
	}

	public ExecutionResult(ExecutionStatus status, String message)
	{
		this.sb = new StringBuilder();
		this.status = status;

		sb.append(message).append('\n');
		this.message = sb.toString();
	}

	public ExecutionResult append(String printable)
	{
		sb.append(printable).append('\n');
		message = sb.toString();
		return this;
	}

	public ExecutionStatus getStatus()
	{
		return status;
	}

	public String getMessage()
	{
		return message;
	}

	@Override
	public String toString()
	{
		return message;
	}
}
