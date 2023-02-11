package com.mitrian.lab.properties;

import java.util.Objects;

public class Property implements EntityProperty
{

	private Object prop;

	public Property(Object property)
	{
		prop = property;
	}

	@Override
	public Object getProperty()
	{
		return prop;
	}

	@Override
	public void setProperty(Object property)
	{
		prop = property;
	}

	@Override
	public String toString()
	{
		if (prop instanceof String)
			return prop.toString();

		if (prop instanceof Mood)
			return ((Mood) prop).toString();

		return null;
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(this) + Objects.hashCode(prop);
	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Property property)
			return hashCode() == property.hashCode();

		return false;
	}
}
