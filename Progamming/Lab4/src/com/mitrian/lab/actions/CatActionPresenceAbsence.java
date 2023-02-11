package com.mitrian.lab.actions;

import com.mitrian.lab.util.exceptions.InappropriateCatStateException;
import java.util.Objects;

public class CatActionPresenceAbsence implements ActionInterface
{

	private String action;
	private CatState state;

	public CatActionPresenceAbsence()
	{
		state = null;
		action = "";
	}

	public void goAway() throws InappropriateCatStateException
	{
		if (state == CatState.HERE || state == null)
		{
			state = CatState.LEFT;
			action = "Он исчез.";
		}
		else
			throw new InappropriateCatStateException("Cat is already left");
	}

	public void returnBack() throws InappropriateCatStateException
	{
		if (state == CatState.LEFT || state == null)
		{
			state = CatState.HERE;
			action = "Как вдруг он появился снова.";
		}
		else
			throw new InappropriateCatStateException("Cat is already here");
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
