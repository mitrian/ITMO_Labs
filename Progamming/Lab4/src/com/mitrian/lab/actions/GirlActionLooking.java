package com.mitrian.lab.actions;

import java.util.Objects;

public class GirlActionLooking implements ActionInterface
{

	private final String action;

	public GirlActionLooking()
	{
		action = "Но все-таки она долго не могла отвести глаз от ветки, на которой только что сидел кот. ";
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
		if (o instanceof GirlActionLooking gal)
			return hashCode() == gal.hashCode() && action.equals(gal.getAction());

		return false;
	}
}
