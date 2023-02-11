package com.mitrian.lab.actions;

import java.util.Objects;

public class CatActionPresenceAbsence implements ActionInterface
{

	private final String action;
	private CatState state;

	public CatActionPresenceAbsence(CatState state)
	{
		if (state == CatState.LEFT)
			action = "Он исчез.";
		else
			action = "Как вдруг он появился снова.";
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
		return Objects.hashCode(this) + Objects.hashCode(action) + Objects.hashCode(state);
	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof CatActionPresenceAbsence capa)
			return hashCode() == capa.hashCode() && action.equals(capa.getAction());

		return false;
	}

}
