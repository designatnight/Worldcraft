package com.clinkworks.worldcraft.datatypes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SequenceID {
	
	@JsonProperty
	private Integer id;
	
	@JsonProperty
	private String fullName;
	
	private String sequenceName;
	
	private String sequenceID;
	
	private final static String COLON = ":";
	
	/**
	 * depending on this class, the generated sequence will look like this:
	 * SequenceID:1
	 * @param id
	 */
	public SequenceID(Integer id){
		this.id = id;
		sequenceName = getClass().getSimpleName();
		fullName = new StringBuilder().
				append(getClass().getName()).
				append(COLON).
				append(id)
			.toString();
		
		sequenceID = new StringBuilder().
				append(sequenceName).
				append(COLON).
				append(id).
			toString();
	}
	
	public SequenceID(){
		
	}
	
	/**
	 * returns the integer value of this id, this may be changed to a string later
	 * to accomidate different database types.
	 * @return
	 */
	public Integer getID(){
		return id;
	}
	
	/**
	 * the full name of this sequence id, for example the ItemID:
	 * com.clinkworks.gameengine.datatypes.ItemID:1
	 * @return
	 */
	public String getFullName(){
		return fullName;
	}
	
	/**
	 * the short name of this sequence id
	 * 
	 * @return
	 */
	public String getSequenceID(){
		return sequenceID;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof SequenceID){
			SequenceID objSequence = (SequenceID)obj;
			boolean retval = this.getFullName().equals(objSequence.getFullName());
			if(retval == false){
				boolean sequenceNamesMatch = this.getSequenceName().equals(objSequence.getClass().getSimpleName());
				boolean sequenceIDsMatch = this.getID().equals(objSequence.getID());
				retval = sequenceIDsMatch && sequenceNamesMatch;
			}
			return retval;
		}
		return false;
	}
	
	public String getSequenceName(){
		return sequenceName;
	}
	
	@Override
	public String toString(){
		return getSequenceID();
	}	
	
	/**
	 * I added this to allow these to be easly and uniquly hashable by thier package name and sequence number.
	 */
	@Override
	public int hashCode(){
		return getFullName().hashCode();
	}
}
