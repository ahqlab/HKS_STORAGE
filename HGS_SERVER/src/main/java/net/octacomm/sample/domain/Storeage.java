package net.octacomm.sample.domain;

import lombok.Data;

@Data
public class Storeage implements Domain{

	private int id;
	
	private int sms;
	
	private int ctmIdx;
	
	private String customerName;
	
	private String modelName;
	
	private String storeageSn;
	
	private String productSpec;
	
	private String installDate;
	
	private String installArea;
	
	private String stgIp;
	
	private int pIdx;
	
	private String createDate;
	
}
