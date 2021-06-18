package com.hankisul.storage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hankisul.storage.dao.mapper.OverlabTermMapper;
import com.hankisul.storage.domain.OverlabTerm;

@Component
@Service("overlabTermService")
public class OverlabTermServiceImpl implements OverlabTermService{
	
	@Autowired
	private OverlabTermMapper mapper;
	
	@Override
	public int insert(OverlabTerm paramOverlabTerm) {
		return mapper.insert(paramOverlabTerm);
	}

	@Override
	public List<OverlabTerm> getList() {
		return mapper.getList();
	}

	@Override
	public int delete(Integer paramInteger) {
		return mapper.delete(paramInteger);
	}

	@Override
	public int update(OverlabTerm paramOverlabTerm) {
		return mapper.update(paramOverlabTerm);
	}

	@Override
	public OverlabTerm get(Integer paramInteger) {
		return mapper.get(paramInteger);
	}

	@Override
	public OverlabTerm getObjOfPidx(int paramInt) {
		return mapper.getObjOfPidx(paramInt);
	}

}
