package com.mitrian.lab.actions;

import java.util.Arrays;

public class ActionInteractionWithAuthor implements ActionInterface
{
	private final String[] phrases;

	public ActionInteractionWithAuthor()
	{
		phrases = new String[3];
		Arrays.fill(phrases, "");
	}

	public void addAuthorPhrase(String phrase)
	{
		phrases[1] = phrase;
	}

	public void addEntityFirstPhrase(String phrase)
	{
		phrases[0] = phrase;
	}

	public void addEntitySecondPhrase(String phrase)
	{
		phrases[2] = phrase;
	}

	@Override
	public String getAction()
	{
		StringBuilder sb = new StringBuilder();
		sb
				.append(phrases[0])
				.append(",- ")
				.append(phrases[1]);

		if (!phrases[2].equals(""))
		{
			sb
					.append(",- ")
					.append(phrases[2]);
		}

		return sb.toString();
	}
}
