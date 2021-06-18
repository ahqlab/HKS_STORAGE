package com.hankisul.storage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hankisul.storage.dao.mapper.OverlabTermMapper;
import com.hankisul.storage.dao.mapper.UserMapper;
import com.hankisul.storage.domain.OverlabTerm;
import com.hankisul.storage.domain.OverlabTermParam;
import com.hankisul.storage.domain.User;

@RequestMapping({ "/setting/term" })
@Controller
public class OverlabTermController
		extends AbstractCRUDController<OverlabTermMapper, OverlabTerm, OverlabTermParam, Integer> {

	@Autowired
	private UserMapper UserMapper;

	@Autowired
	public void setCRUDMapper(OverlabTermMapper mapper) {
		this.mapper = mapper;
	}

	protected Class<OverlabTerm> getDomainClass() {
		return OverlabTerm.class;
	}

	@RequestMapping(value = { "/update2" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public void form(Model model, @RequestParam("pIdx") int pIdx, HttpSession session) {
		model.addAttribute("domain", ((OverlabTermMapper) this.mapper).getObjOfPidx(pIdx));
	}

	protected String getRedirectUrl(HttpSession session) {
		int id = ((Integer) session.getAttribute("id")).intValue();
		return "redirect:/setting/term/update2?pIdx=" + id;
	}

	@ModelAttribute
	public void addAttributes(HttpSession session, Model model) {
		int role = ((Integer) session.getAttribute("role")).intValue();
		int id = ((Integer) session.getAttribute("id")).intValue();
		User user = this.UserMapper.get(Integer.valueOf(id));
		model.addAttribute("role", Integer.valueOf(user.getRole()));
		model.addAttribute("pIdx", Integer.valueOf(user.getId()));
	}
}