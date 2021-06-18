package com.hankisul.storage.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hankisul.storage.dao.mapper.EmailLogMapper;
import com.hankisul.storage.domain.Email;

@RequestMapping({ "/rest/get" })
@Controller
public class RestController {

	@Autowired
	private EmailLogMapper logMapper;

	@ResponseBody
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public List<Email> login() throws IOException {
		return this.logMapper.getList();
	}

	@ResponseBody
	@RequestMapping(value = { "/intrfc/saveDeviceToken.do" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST }, headers = { "Accept=application/json" })
	public List<Email> saveDeviceToken() throws Exception {
		return this.logMapper.getList();
	}
}