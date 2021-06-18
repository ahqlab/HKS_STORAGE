package com.hankisul.storage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hankisul.storage.dao.mapper.ServerStatusMapper;
import com.hankisul.storage.domain.ServerStatus;

@RequestMapping({ "/set/server/status" })
@Controller
public class ServerStatusController {

	@Autowired
	private ServerStatusMapper serverStatusMapper;

	@ResponseBody
	@RequestMapping(value = { "/get" }, method = { RequestMethod.GET })
	private ServerStatus get(@RequestParam("userIdx") int userIdx, HttpSession session) {
		return serverStatusMapper.get(userIdx);
	}

	@ResponseBody
	@RequestMapping(value = { "/set" }, method = { RequestMethod.GET })
	private boolean set(@RequestParam("userIdx") int userIdx, @RequestParam("status") int status, HttpSession session) {
		return serverStatusMapper.set(userIdx, status);
	}
}
