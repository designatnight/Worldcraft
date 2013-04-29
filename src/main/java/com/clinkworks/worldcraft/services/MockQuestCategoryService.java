package com.clinkworks.worldcraft.services;

import java.util.List;

import com.clinkworks.worldcraft.domain.QuestCategory;
import com.google.inject.Singleton;

@Singleton
public class MockQuestCategoryService extends QuestCategoryService{

	public enum CategoryID{COMBAT, GATHER, TRADE_SKILL, FED_EX};
	
	public MockQuestCategoryService(){
		super();
		List<QuestCategory> categories = getQuestCategories();
		
		categories.add(new QuestCategory(CategoryID.COMBAT.name(), "Combat", "Combat (kill x number of mobs)"));
		categories.add(new QuestCategory(CategoryID.GATHER.name(), "Gather", "Gather (Gather x number of items)"));
		categories.add(new QuestCategory(CategoryID.TRADE_SKILL.name(), "Trade skill", "Trade skill (create x)"));
		categories.add(new QuestCategory(CategoryID.FED_EX.name(), "Fed ex", "Fex ex (deliver x)"));
		
	}

	
}
