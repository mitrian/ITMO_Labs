package com.mitrian.lab;

import com.mitrian.lab.actions.*;
import com.mitrian.lab.entities.AbstractEntity;
import com.mitrian.lab.entities.Cat;
import com.mitrian.lab.entities.Girl;
import com.mitrian.lab.properties.Mood;
import com.mitrian.lab.util.Builder;
import com.mitrian.lab.util.Printer;
import com.mitrian.lab.util.exceptions.InappropriateCatStateException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main
{

	private class ConsolePrinter implements Printer
	{
		@Override
		public void print(String printable)
		{
			System.out.println(printable);
		}
	}

	public static class Repository
	{
		private final List<ActionInterface> actions;
		private final List<AbstractEntity> members;

		private Repository()
		{
			actions = new LinkedList<>();
			members = new LinkedList<>();
		}

		public void addAction(ActionInterface action)
		{
			if (action != null)
				actions.add(action);

		}

		public void addMember(AbstractEntity entity)
		{
			if (entity != null )
				members.add(entity);
		}

		public List<ActionInterface> getActions()
		{
			return actions;
		}

		public List<AbstractEntity> getMembers()
		{
			return members;
		}

		public void addActions(ActionInterface... actions)
		{
			if (actions.length != 0)
				this.actions.addAll(Arrays.asList(actions));
		}

		public void addMembers(AbstractEntity... members)
		{
			if (members.length != 0)
				this.members.addAll(Arrays.asList(members));
		}
	}

	private final Printer printer;


	public Main()
	{
		printer = new ConsolePrinter();
		Builder storyBuilder = new Builder()
		{
			private final Main.Repository repository;

			{
				repository = new Main.Repository();

			}

			@Override
			public void addStoryEntities(AbstractEntity... entities)
			{
				repository.addMembers(entities);

			}

			@Override
			public void addStoryActions(ActionInterface... actions)
			{
				repository.addActions(actions);
			}

			@Override
			public void runStory()
			{
				for (ActionInterface action : repository.getActions())
					printer.print(action.getAction());
			}
		};

		AbstractEntity cat = new Cat("", "");
		AbstractEntity girl = new Girl("", "", Mood.NOTSURPRISED, Mood.ACCUSTOMED);
		storyBuilder.addStoryEntities(cat, girl);

		try
		{
			CatActionPresenceAbsence catAbsence = new CatActionPresenceAbsence();
			catAbsence.goAway();
			CatActionPresenceAbsence catPresence = new CatActionPresenceAbsence();
			catPresence.returnBack();

			ActionInterface catInteractionWithAuthorActionOne = new ActionInteractionWithAuthor();
			((ActionInteractionWithAuthor) catInteractionWithAuthorActionOne)
					.addEntityFirstPhrase("Пусть называется как угодно");
			((ActionInteractionWithAuthor) catInteractionWithAuthorActionOne)
					.addAuthorPhrase("сказал Кот");
			((ActionInteractionWithAuthor) catInteractionWithAuthorActionOne)
					.addEntitySecondPhrase("Ты вечером будешь на крокете у Королевы?");


			ActionInterface girlInteractionWithAuthorActionOne = new ActionInteractionWithAuthor();
			((ActionInteractionWithAuthor) girlInteractionWithAuthorActionOne)
					.addEntityFirstPhrase("Ой, я бы очень хотела");
			((ActionInteractionWithAuthor) girlInteractionWithAuthorActionOne)
					.addAuthorPhrase("сказала Алиса");
			((ActionInteractionWithAuthor) girlInteractionWithAuthorActionOne)
					.addEntitySecondPhrase("да только меня что-то еще не приглашали");


			ActionInterface catInteractionWithAuthorActionTwo = new ActionInteractionWithAuthor();
			((ActionInteractionWithAuthor) catInteractionWithAuthorActionTwo)
					.addEntityFirstPhrase("Кстати, чуть не забыл спросить");
			((ActionInteractionWithAuthor) catInteractionWithAuthorActionTwo)
					.addAuthorPhrase("сказал он");
			((ActionInteractionWithAuthor) catInteractionWithAuthorActionTwo)
					.addEntitySecondPhrase("что случилось с тем ребенком?");


			ActionInterface girlInteractionWithAuthorTwo = new ActionInteractionWithAuthor();
			((ActionInteractionWithAuthor) girlInteractionWithAuthorTwo)
					.addEntityFirstPhrase("Он превратился в поросенка");
			((ActionInteractionWithAuthor) girlInteractionWithAuthorTwo)
					.addAuthorPhrase("честно ответила Алиса.");

			storyBuilder.addStoryActions(
					catInteractionWithAuthorActionOne,
					girlInteractionWithAuthorActionOne,
					new CatActionSpeech(cat.speak()),
					catAbsence,
					new AuthorActionSpeech(girl.getMood()),
					new GirlActionLooking(),
					catPresence,
					catInteractionWithAuthorActionTwo,
					girlInteractionWithAuthorTwo
			);

			storyBuilder.runStory();
		}
		catch (InappropriateCatStateException e)
		{
			printer.print(e.getMessage());
		}
	}

	public static void main(String[] args)
	{
		Main main = new Main();
	}

}
