package net.octacomm.sample.service;

import java.util.List;

import net.octacomm.sample.domain.Email;

public interface MailReaderService {
	
	List<Email> getMails(String user, String password);

}
