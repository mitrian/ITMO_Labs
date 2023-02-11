package com.mitrian.lab;

import com.mitrian.lab.actions.*;
import com.mitrian.lab.entities.AbstractEntity;
import com.mitrian.lab.entities.Cat;
import com.mitrian.lab.entities.Girl;
import com.mitrian.lab.properties.Mood;
import com.mitrian.lab.util.Story;
import com.mitrian.lab.util.StoryPrinter;

public class Main
{

	public Main()
	{
		AbstractEntity cat = new Cat("", "");
		AbstractEntity girl = new Girl("", "", Mood.NOTSURPRISED, Mood.ACCUSTOMED);

		Story story = new StoryPrinter(cat, girl);

		story.addStoryAction(new CatActionSpeech(cat.speak()));
		story.addStoryAction(new CatActionPresenceAbsence(CatState.LEFT));
		story.addStoryAction(new AuthorActionSpeech(girl.getMood()));
		story.addStoryAction(new GirlActionLooking());
		story.addStoryAction(new CatActionPresenceAbsence(CatState.HERE));
		story.print();
	}

	public static void main(String[] args)
	{
		Main main = new Main();
	}

}
