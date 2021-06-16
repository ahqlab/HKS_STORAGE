package net.octacomm.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import net.octacomm.sample.dao.mapper.ServerStatusMapper;
import net.octacomm.sample.domain.ServerStatus;

@Component
@Service("serverStatusService")
public class ServerStatusServiceImpl implements ServerStatusService{
	
	@Autowired
	private ServerStatusMapper mapper;
	
	@Override
	public ServerStatus get(int userIdx) {
		return mapper.get(userIdx);
	}

	@Override
	public int insert(ServerStatus domain) {
		return mapper.insert(domain);
	}

}
