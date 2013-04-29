package com.clinkworks.worldcraft.util;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.concurrent.ThreadSafe;

import com.clinkworks.worldcraft.datatypes.SequenceID;
import com.google.inject.Singleton;

@Singleton
@ThreadSafe
public class NonPersistantSequenceGenerator {
	private static Map<String, Integer> _inMemoryLastKeyTable;
	
	static{
		_inMemoryLastKeyTable = new HashMap<String, Integer>();
	}
	
	public <T extends SequenceID> T generateSequence(Class<T> type){
		String sequenceType = type.getName();
		Integer lastInt = _inMemoryLastKeyTable.get(sequenceType);
		if(lastInt == null){
			lastInt = 1;
			_inMemoryLastKeyTable.put(sequenceType, lastInt);
		}else{
			lastInt += 1;
			_inMemoryLastKeyTable.put(sequenceType, lastInt);
		}
		
		try {
			return type.getDeclaredConstructor(Integer.class).newInstance(lastInt);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
