package com.mitrian.lab.actions;

import java.util.Objects;

public class CatActionSpeech implements ActionInterface
{

	private final String action;

	public CatActionSpeech(String action)
	{
		this.action = action + ", - сказал кот. ";
	}

	@Override
	public String getAction()
	{
		return action;
	}

	@Override
	public String toString()
	{
		return "[" + action + "]";
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(this) + Objects.hashCode(action);
	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof CatActionSpeech cas)
			return hashCode() == cas.hashCode() && action.equals(cas.getAction());

		return false;
	}
}
