package com.mitrian.lab.properties;

public enum Mood
{

	ACCUSTOMED("она уже привыкла ко всяким чудесам"),
	NOTSURPRISED("Не сказать, чтобы Алиса так уж сильно этому удивилась");

	private String message;
	Mood(String message)
	{
		this.message = message;
	}

	@Override
	public String toString()
	{
		return message;
	}

}
