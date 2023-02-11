package com.mitrian.lab.properties;

import com.mitrian.lab.util.exceptions.UnsupportedTypeOfEntityPropertyParameter;
import java.util.Objects;

public class Property implements EntityProperty
{

	private Object prop;

	public Property(Object property)
	{
		if (property instanceof String || property instanceof Mood)
			prop = property;
		else
			throw new UnsupportedTypeOfEntityPropertyParameter("Passed parameter type is not currently supported");
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

		return "";
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
