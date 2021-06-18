package com.hankisul.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hankisul.storage.dao.mapper.EmailLogMapper;
import com.hankisul.storage.domain.Email;

@Component
@Service("emailLogService")
public class EmailLogServiceImpl implements EmailLogService{
	
	@Autowired
	private EmailLogMapper emailLogMapper;
	
	@Override
	public int insert(Email paramEmail) {
		return emailLogMapper.insert(paramEmail);
	}

	@Override
	public List<Email> getList() {
		return emailLogMapper.getList();
	}

	@Override
	public int delete(Integer paramInteger) {
		return emailLogMapper.delete(paramInteger);
	}

	@Override
	public int update(Email paramEmail) {
		return emailLogMapper.update(paramEmail);
	}

	@Override
	public Email get(Integer paramInteger) {
		return emailLogMapper.get(paramInteger);
	}

	@Override
	public int getCount() {
		return emailLogMapper.getCount();
	}

	@Override
	public List<Email> existDuplicateEmail(Email paramEmail) {
		return emailLogMapper.existDuplicateEmail(paramEmail);
	}

	@Override
	public int checkSendMMS(int paramInt) {
		return emailLogMapper.checkSendMMS(paramInt);
	}

	@Override
	public Email getListRow() {
		return emailLogMapper.getListRow();
	}

}
