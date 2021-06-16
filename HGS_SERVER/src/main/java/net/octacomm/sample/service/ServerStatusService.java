package net.octacomm.sample.service;

import org.springframework.stereotype.Service;

import net.octacomm.sample.domain.ServerStatus;


public interface ServerStatusService {
	
	ServerStatus get(int userIdx);
	
	
	int insert(ServerStatus domain);

}
