package net.octacomm.sample.service;

import java.util.List;

import net.octacomm.sample.domain.OverlabTerm;

public interface OverlabTermService {

	public int insert(OverlabTerm paramOverlabTerm);

	public List<OverlabTerm> getList();

	public int delete(Integer paramInteger);

	public int update(OverlabTerm paramOverlabTerm);

	public OverlabTerm get(Integer paramInteger);

	public OverlabTerm getObjOfPidx(int paramInt);
}
