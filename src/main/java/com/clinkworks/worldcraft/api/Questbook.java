package com.clinkworks.worldcraft.api;

import java.util.ArrayList;
import java.util.List;

import com.clinkworks.worldcraft.domain.Quest;
import com.clinkworks.worldcraft.domain.QuestID;

public class Questbook {
	private List<Quest> quests;
	
	public Questbook(){
		quests = new ArrayList<Quest>();
	}
	
	public Questbook(List<Quest> quests){
		this.quests = quests;
	}
	
	public List<Quest> getQuests(){
		return quests;
	}
	
	public void addQuest(Quest quest){
		quests.add(quest);
	}
	
	public Quest getQuestByQuestID(QuestID questID){
		for(Quest quest : getQuests()){
			if(quest.getQuestID().equals(questID)){
				return quest;
			}
		}
		return null;
	}
	
	public Quest removeQuest(Quest quest){
		if(!quests.remove(quest)){
			quest = getQuestByQuestID(quest.getQuestID());
			quests.remove(quest);
		}
		return quest;
	}
}
