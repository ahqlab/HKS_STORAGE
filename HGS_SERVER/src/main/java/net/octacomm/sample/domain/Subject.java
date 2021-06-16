package net.octacomm.sample.domain;

import lombok.Data;

@Data
public class Subject implements Domain{

	public Subject(String eventName, String storeageName, String uId, String ip, String dateTime) {
		this.eventName = eventName;
		this.storeageName = storeageName;
		this.uId = uId;
		this.ip = ip;
		this.dateTime = dateTime;
	}

	private String eventName;
	
	private String storeageName;
	
	private String uId;
	
	private String ip;
	
	private String dateTime;
}
