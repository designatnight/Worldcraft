package com.clinkworks.worldcraft.domain;

import javax.annotation.concurrent.NotThreadSafe;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "quest")
@NotThreadSafe
public class QuestCategory {
	
	@JsonProperty
	private String id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String description;
	
	public QuestCategory(String id, String name, String description){
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public QuestCategory(){
		
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}
}
