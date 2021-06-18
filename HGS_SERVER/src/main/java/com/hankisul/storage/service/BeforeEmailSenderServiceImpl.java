package com.hankisul.storage.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.hankisul.storage.dao.mapper.EmailLogMapper;

public class BeforeEmailSenderServiceImpl implements BeforeEmailSenderService{
	
	@Autowired
	private EmailLogMapper emailLogMapper;
	
	@Override
	public void checkBeforMail() {
		// TODO Auto-generated method stub
		
	}

}
