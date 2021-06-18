package com.hankisul.storage.domain;

import lombok.Data;

@Data
public class ResponceGabia {
	
	private String access_token;
	
	private String refresh_token;
	
	private int expires_in;
	
	private String scope;
	
	private String create_on;
	
	private String is_expires;
	
	private String token_type;
	
	private String code;
}
