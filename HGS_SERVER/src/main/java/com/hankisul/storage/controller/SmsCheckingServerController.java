package com.hankisul.storage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hankisul.storage.dao.mapper.ServerStatusMapper;
import com.hankisul.storage.dao.mapper.UserMapper;
import com.hankisul.storage.domain.User;
import com.hankisul.storage.service.ProgrammableScheduler;
import com.hankisul.storage.test.MainJobThread;
import com.hankisul.storage.utils.CommonUtil;

@RequestMapping({ "/server" })
@Controller
public class SmsCheckingServerController {

	
	@Autowired 
	ProgrammableScheduler scheduler;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ServerStatusMapper serverStatusMapper;

	@ResponseBody
	@RequestMapping(value = { "/change/status" }, method = { RequestMethod.GET })
	public void changeServerStatus(HttpSession session, @RequestParam("status") boolean status) {
		try {
			int id = ((Integer) session.getAttribute("id")).intValue();
			User user = this.userMapper.get(Integer.valueOf(id));
			if(id != 0) {
				if(serverStatusMapper.get(user.getId())  != null) {
					if(!status) {
						serverStatusMapper.deleteOne(user.getId());
					}
				}else {
					if(status) {
						if(!CommonUtil.checkString(user.getServiceEmail()) && !CommonUtil.checkString(user.getServiceEmailPassword())  ) {
							System.err.println("쓰레드를 시작합니다.....");
							MainJobThread mainJob = new MainJobThread(user.getId(), user.getServiceEmail(), user.getServiceEmailPassword(), status);
							Thread td = new Thread(mainJob);
							td.start();
						}
					}
				}
			}
			return;
		}catch (Exception e) {
			System.err.println("ERROR : " + e.getMessage() );
		}
		
		
		//return;
		//try {
			//int id = ((Integer) session.getAttribute("id")).intValue();
			//User user = this.userMapper.get(Integer.valueOf(id));
			//scheduler.startScheduler(id, user.getServiceEmail(), user.getServiceEmailPassword(), status);
		//}catch (NullPointerException e) {
		//	System.err.println("null 입니다.");
		//}
		
		
		
		
		
		//if (status) {
		//	int id = ((Integer) session.getAttribute("id")).intValue();
		//	User user = this.userMapper.get(Integer.valueOf(id));
		//	scheduler.startScheduler(user.getServiceEmail(), user.getServiceEmailPassword());
		//} else {
		//	scheduler.stopScheduler();
	//	}
	}

	@ResponseBody
	@RequestMapping(value = { "/current/status" }, method = { RequestMethod.GET })
	public boolean currentServerStatus(HttpSession session) {
		try {
			int id = ((Integer) session.getAttribute("id")).intValue();
			User user = this.userMapper.get(Integer.valueOf(id));
			return serverStatusMapper.get(user.getId()) != null ? true : false;
		}catch (Exception e) {
			return false;
		}
	}

}