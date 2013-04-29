package com.clinkworks.worldcraft.modules;

import com.clinkworks.worldcraft.services.MockQuestCategoryService;
import com.clinkworks.worldcraft.services.QuestCategoryService;
import com.google.inject.AbstractModule;

public class ModuleBase extends AbstractModule{

	@Override
	protected void configure() {
		bind(QuestCategoryService.class).to(MockQuestCategoryService.class);
		install(new RestModule());
	}
	
}
