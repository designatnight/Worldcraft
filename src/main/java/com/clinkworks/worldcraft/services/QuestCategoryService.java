package com.clinkworks.worldcraft.services;

import java.util.ArrayList;
import java.util.List;

import com.clinkworks.worldcraft.domain.QuestCategory;
import com.google.inject.Singleton;

@Singleton
public class QuestCategoryService {
	private List<QuestCategory> categories;

	public QuestCategoryService(){
		categories = new ArrayList<QuestCategory>();
	}
	
	public List<QuestCategory> getQuestCategories(){
		return categories;
	}
	
	public QuestCategory getQuestCategoryByID(String id){
		for(QuestCategory questCategory : categories){
			if(questCategory.getID().equals(id)){
				return questCategory;
			}
		}
		return null;
	}
}
