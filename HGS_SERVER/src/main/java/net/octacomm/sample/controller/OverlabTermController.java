package net.octacomm.sample.controller;

import javax.servlet.http.HttpSession;
import net.octacomm.sample.dao.mapper.OverlabTermMapper;
import net.octacomm.sample.dao.mapper.UserMapper;
import net.octacomm.sample.domain.OverlabTerm;
import net.octacomm.sample.domain.OverlabTermParam;
import net.octacomm.sample.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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