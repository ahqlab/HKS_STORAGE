package com.hankisul.storage.domain;

import lombok.Data;

@Data
public class Customer implements Domain{
	
	private int id;
	  private String customerName;
	  private String email;
	  private String managerName;
	  private String managerHP;
	  private String sendNumber;
	  private String createDate;
	  private int sms;
	  private int pIdx;

}
