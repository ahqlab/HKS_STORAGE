package com.hankisul.storage.domain;

import lombok.Data;

@Data
public class ServerStatus implements Domain{

	public ServerStatus() {
		super();
	}

	public ServerStatus(int userIdx, int threadIdx, int status) {
		super();
		this.userIdx = userIdx;
		this.threadIdx =  threadIdx;
		this.status = status;
	}

	private int id;
	
	private int userIdx;
	
	private int threadIdx;
	
	private int status;
	
	
	
}
