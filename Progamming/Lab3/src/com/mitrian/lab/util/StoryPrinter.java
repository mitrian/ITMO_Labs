package com.mitrian.lab.util;

import com.mitrian.lab.actions.ActionInterface;
import com.mitrian.lab.entities.AbstractEntity;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class StoryPrinter implements Story
{

	private final List<ActionInterface> actions;
	private final List<AbstractEntity> members;


	public StoryPrinter(AbstractEntity... entities)
	{
		actions = new LinkedList<>();
		members = new LinkedList<>();

		if (entities.length != 0)
			members.addAll(Arrays.stream(entities).toList());
	}

	private String generateString()
	{
		StringBuilder sb = new StringBuilder();

		for (ActionInterface ai : actions)
			sb.append(ai.getAction()).append("\n");

		return sb.toString();
	}

	@Override
	public void print()
	{
		System.out.println(generateString());
	}

	@Override
	public void addStoryAction(ActionInterface action)
	{
		actions.add(action);
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append("Story printer info\n");
		sb.append("Actions: ").append(actions.toString()).append("\n");
		sb.append("Actions: ").append(members.toString()).append("\n");

		return sb.toString();
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(this) + Objects.hashCode(actions) + Objects.hashCode(members);
	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof StoryPrinter sp)
			return hashCode() == sp.hashCode();

		return false;
	}
}
