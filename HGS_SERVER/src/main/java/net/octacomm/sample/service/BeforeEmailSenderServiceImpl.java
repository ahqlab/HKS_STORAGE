package net.octacomm.sample.service;

import org.springframework.beans.factory.annotation.Autowired;

import net.octacomm.sample.dao.mapper.EmailLogMapper;

public class BeforeEmailSenderServiceImpl implements BeforeEmailSenderService{
	
	@Autowired
	private EmailLogMapper emailLogMapper;
	
	@Override
	public void checkBeforMail() {
		// TODO Auto-generated method stub
		
	}

}
