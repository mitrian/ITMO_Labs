package com.mitrian.lab.common.commands.utils;

import java.io.Serializable;

public class ExecutionResult implements Serializable {

	private final StringBuilder stringBuilder;

	public ExecutionResult()
	{
		this.stringBuilder = new StringBuilder();
	}

	public StringBuilder appendNewLine(String line) {
		return stringBuilder.append(line).append('\n');
	}

	public StringBuilder getStringBuilder() {
		return stringBuilder;
	}

	@Override
	public String toString() {
		return stringBuilder.toString();
	}
}
