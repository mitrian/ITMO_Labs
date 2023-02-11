package com.mitrian.lab.util;

import com.mitrian.lab.actions.ActionInterface;
import com.mitrian.lab.entities.AbstractEntity;

public interface Builder
{

	void addStoryEntities(AbstractEntity... entities);

	void addStoryActions(ActionInterface... actions);

	void runStory();

}
