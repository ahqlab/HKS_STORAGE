package com.hankisul.storage.domain;

import lombok.Data;

@Data
public class ServerResponse {

	public ServerResponse(String responseMessage, int status) {
		this.responseMessage = responseMessage;
		this.status = status;
	}

	private String responseMessage;
	
	private int status;
	
}
