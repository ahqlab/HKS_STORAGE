package net.octacomm.sample.service;

import org.springframework.stereotype.Service;

@Service
public interface BeforeEmailSenderService {
	
	void checkBeforMail();
}
