package com.clinkworks.worldcraft.exceptions;

import java.lang.reflect.Field;

import com.clinkworks.worldcraft.domain.Quest;

public class CouldNotCopyQuestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3566704773458596788L;
	
	public CouldNotCopyQuestException(Field field, Quest thisQuest, Quest thatQuest, Exception e){
		super("Could not copy field " + field + " during copy of " + thatQuest + " into " + thisQuest + "\n\treason: " + e.getMessage());
	}

}
