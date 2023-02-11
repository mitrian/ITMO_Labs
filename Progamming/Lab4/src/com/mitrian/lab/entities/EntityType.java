package com.mitrian.lab.entities;

public enum EntityType
{
	CAT("кот"),
	GIRL("девочка");

	private String descriptor;

	EntityType(String descriptor)
	{
		this.descriptor = descriptor;
	}

	@Override
	public String toString()
	{
		return descriptor;
	}

}
