package com.hankisul.storage.service;

import java.util.List;

import com.hankisul.storage.domain.Email;

public interface MailReaderService {
	
	List<Email> getMails(String user, String password);

}
