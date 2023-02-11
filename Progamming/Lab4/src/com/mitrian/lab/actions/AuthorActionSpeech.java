package com.mitrian.lab.actions;

import java.util.Objects;

public class AuthorActionSpeech implements ActionInterface
{

	private final String action;

	public AuthorActionSpeech(String action)
	{
		this.action = action;
	}

	@Override
	public String getAction()
	{
		return action;
	}

	@Override
	public String toString()
	{
		return action;
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(this) + Objects.hashCode(action);
	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof AuthorActionSpeech aas)
			return hashCode() == aas.hashCode() && action.equals(aas.getAction());

		return false;
	}
}
