package net.octacomm.sample.service;

import java.util.List;


import net.octacomm.sample.domain.Email;

public interface EmailLogService {
	
	public abstract int insert(Email paramEmail);
	  
	public List<Email> getList();
	  
	public int delete(Integer paramInteger);
	  
	public int update(Email paramEmail);
	  
	public Email get(Integer paramInteger);
	  
	public int getCount();
	  
	public List<Email> existDuplicateEmail(Email paramEmail);
	  
	public int checkSendMMS(int paramInt);

	public Email getListRow();

}
