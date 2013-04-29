package com.clinkworks.worldcraft.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.clinkworks.worldcraft.datatypes.SequenceID;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "quest")
public class QuestID extends SequenceID{

	public QuestID(Integer id) {
		super(id);
	}

	public QuestID(){
		
	}
}
