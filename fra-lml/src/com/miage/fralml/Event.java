package com.miage.fralml;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author Aleks
 *  *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Event {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	public Long key;
	
	
	// nom de l'event
	@Persistent
	public String name;
	
	@Persistent
	public String place;
	
	@Persistent
	public String date;	
	
	@Persistent
	public List<String> players;
	
	
	@Persistent
	public String desc;

	@Persistent
	public String longitude;
	
	@Persistent
	public String latitude;
	
	@Persistent
	public String url;

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}




	

}