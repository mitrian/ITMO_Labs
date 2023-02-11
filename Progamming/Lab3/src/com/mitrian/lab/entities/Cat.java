package com.mitrian.lab.entities;

import java.util.Objects;

public class Cat extends AbstractEntity
{
	public Cat(String name, String location)
	{
		super(name, location);
	}

	@Override
	public String speak()
	{
		return "Значит до вечера";
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append("Сущность: кот").append("\n");
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
