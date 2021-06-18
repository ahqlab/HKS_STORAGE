package com.hankisul.storage.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Service;

import com.hankisul.storage.dao.mapper.ServerStatusMapper;

@Service
public class ServerLifeCycleService
		implements ApplicationListener<ContextClosedEvent>, InitializingBean, DisposableBean {
	
	@Autowired
	private ServerStatusMapper serverStatusMapper;
	
	@PostConstruct
	private void init() {
		System.err.println("PostConstruct annotation으로 빈이 완전히 생성된 후에 한 번 수행될 메서드에 붙입니다.");
		serverStatusMapper.deleteAll();
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		// TODO Auto-generated method stub

	}

}
