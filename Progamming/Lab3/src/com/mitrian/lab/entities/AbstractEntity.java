package com.mitrian.lab.entities;

import com.mitrian.lab.properties.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractEntity
{

	private final EntityType type;

	private final EntityProperty name;
	private final EntityProperty location;
	private final List<EntityProperty> moods;

	public AbstractEntity(String name, String location)
	{
		this.type = EntityType.CAT;
		this.name = new Property(name);
		this.location = new Property(location);
		moods = null;
	}

	public AbstractEntity(String name, String location, Mood... mood)
	{
		this.type = EntityType.GIRL;
		this.name = new Property(name);
		this.location = new Property(location);

		this.moods = new LinkedList<>();
		if (mood.length != 0)
			this.moods.addAll(Arrays.stream(mood).map(Property::new).toList());
	}

	public void setName(String name)
	{
		this.name.setProperty(name);
	}

	public String getName()
	{
		return name.toString();
	}

	public void setLocation(String location)
	{
		this.location.setProperty(location);
	}

	public String getLocation()
	{
		return location.toString();
	}

	public void addMood(Mood... mood)
	{
		if (moods != null && type == EntityType.GIRL && mood.length != 0)
			moods.addAll(Arrays.stream(mood).map(Property::new).toList());
	}

	public String getMood()
	{
		if (moods != null && type == EntityType.GIRL)
		{
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < moods.size(); i++)
			{
				sb.append(moods.get(i));

				if (i < moods.size()-1)
					sb.append(", ");
				else
					sb.append(". ");
			}

			return sb.toString();
		}

		return "Mood is not set for this entity";
	}

	public abstract String speak();

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append("Абстрактная сущность").append("\n");
		sb.append("Имя: ").append(name.getProperty()).append("\n");
		sb.append("Место: ").append(location.getProperty()).append("\n");

		if (type == EntityType.GIRL)
			sb.append("Настроение: ").append(getMood()).append("\n");

		return sb.toString();
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(this)
				+ Objects.hashCode(type)
				+ Objects.hashCode(name)
				+ Objects.hashCode(location)
				+ Objects.hashCode(moods);
	}

	@Override
	public boolean equals(Object o)
	{
		if (o instanceof AbstractEntity ae)
			return hashCode() == ae.hashCode() && name.equals(ae.name);

		return false;
	}
}
