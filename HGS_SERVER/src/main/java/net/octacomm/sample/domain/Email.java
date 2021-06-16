package net.octacomm.sample.domain;

import java.util.List;

import lombok.Data;

@Data
public class Email implements Domain{
	
	public Email() {
		
	}
	
	public Email(String status , String subject, String mailFrom, String mailDate, String body){
		String[] fArray = mailFrom.split("\\<");
		System.err.println(fArray.length);
		this.status = status.trim();
		this.mailFrom = fArray[fArray.length - 1].trim().replace(">", "");
		//this.mailFrom = mailFrom;
		this.mailDate = mailDate.trim();
		this.body = body.trim();
		addSubjectItems(subject);
	}
	
	public void addSubjectItems(String subject) {
		String[] array = subject.split(",");
		eventName = array[0].trim();
		storeageName = array[1].replace("Name:", "").trim();
		uId = array[2].trim();
		ip = array[3].replace("IP", "").trim(); 
		dateTime = array[4].replace("mail date/time:", "").trim();
	}
	
	private int id;
	
	private String status;
	
	private String eventName;
	
	private String storeageName;
	
	private String uId;
	
	private String ip;
	
	private String dateTime;
	
	private String mailFrom;
	
	private String mailDate;
	
	private String body;
	
	private int sendMMS;

}
