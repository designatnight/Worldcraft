package com.clinkworks.worldcraft.domain;

import java.lang.reflect.Field;
import java.util.Date;

import javax.annotation.concurrent.NotThreadSafe;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

import com.clinkworks.worldcraft.exceptions.CouldNotCopyQuestException;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "quest")
@NotThreadSafe
public class Quest {
	
	@JsonProperty
	private QuestID questID;
	
	@JsonProperty
	private QuestCategory questCategory;	
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String description;
	
	@JsonProperty
	private String background;
	
	@JsonProperty
	private String reward;
	
	@JsonProperty
	private String storyline;
	
	@JsonProperty
	private String difficulty;
	
	@JsonProperty
	private Date creationDate;
	
	public Quest(QuestID questID, QuestCategory questCategory){
		this.questID = questID;
		this.creationDate = new Date();
		this.questCategory = questCategory;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public void setStoryline(String storyline) {
		this.storyline = storyline;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * note this constructor should only be used by jackson
	 */
	public Quest(){
		this.creationDate = new Date();
	}
	
	public QuestID getQuestID(){
		return questID;
	}
	
	public Date getCreationDate(){
		return creationDate;
	}
	
	public String getQuestName(){
		return name;
	}
	
	public void setQuestName(String name){
		this.name = name;
	}
	
	public String getQuestDescription(){
		return description;
	}
	
	public void setQuestDescription(String description){
		this.description = description;
	}
	
	public QuestCategory getQuestCategory() {
		return questCategory;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getBackground() {
		return background;
	}

	public String getReward() {
		return reward;
	}

	public String getStoryline() {
		return storyline;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Quest: {[QuestID=");
		sb.append(getQuestID());
		sb.append("] [Name=");
		sb.append(getQuestName());
		sb.append("] [Description=");
		sb.append(getQuestDescription());
		sb.append("]}");
		return sb.toString();
	}	
	
	public Quest shallowCopyQuest(Quest quest){
		for(Field field : getClass().getDeclaredFields()){
			if(isRelevantField(field, quest)){
				field.setAccessible(true);
				try {
					field.set(this, field.get(quest));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new CouldNotCopyQuestException(field, this, quest, e);
				}
			}
		}
		
		return this;
	}
	
	private boolean isRelevantField(Field field, Quest target){
		
		boolean isJsonProperty = field.isAnnotationPresent(JsonProperty.class);
		boolean isNotNull = false;
		
		if(isJsonProperty){
			try {
				isNotNull = field.get(target) != null;
			} catch (IllegalArgumentException | IllegalAccessException e) {
				throw new CouldNotCopyQuestException(field, this, target, e);
			}
		}
		
		return isJsonProperty && isNotNull;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
}
