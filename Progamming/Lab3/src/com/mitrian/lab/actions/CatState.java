package com.mitrian.lab.actions;

public enum CatState
{
	HERE("появился"),
	LEFT("исчез");

	private String descriptor;
	CatState(String descriptor)
	{
		this.descriptor = descriptor;
	}

	@Override
	public String toString()
	{
		return super.toString();
	}
}
