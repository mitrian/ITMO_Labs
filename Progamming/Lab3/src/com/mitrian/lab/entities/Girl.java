package com.mitrian.lab.entities;

import com.mitrian.lab.properties.Mood;
import java.util.Objects;

public class Girl extends AbstractEntity
{

	public Girl(String name, String location, Mood... mood)
	{
		super(name, location, mood);
	}

	@Override
	public String speak()
	{
		return "";
	}


	public String speak1()
	{
		return "rrr";
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append("Сущность: девочка").append("\n");
		sb.append("Имя: ").append(getName()).append("\n");
		sb.append("Место: ").append(getLocation()).append("\n");
		sb.append("Настроение: ").append(getMood()).append("\n");
		return sb.toString();
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(this)
				+ Objects.hashCode(getName())
				+ Objects.hashCode(getLocation())
				+ Objects.hashCode(getMood());
	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Cat c)
			return hashCode() == c.hashCode() && getName().equals(c.getName());

		return false;
	}
}
