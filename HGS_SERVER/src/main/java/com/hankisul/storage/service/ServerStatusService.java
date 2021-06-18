package com.hankisul.storage.service;

import org.springframework.stereotype.Service;

import com.hankisul.storage.domain.ServerStatus;


public interface ServerStatusService {
	
	ServerStatus get(int userIdx);
	
	
	int insert(ServerStatus domain);

}
