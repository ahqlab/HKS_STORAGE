package net.octacomm.sample.controller;

import javax.servlet.http.HttpSession;
import net.octacomm.sample.dao.mapper.EmailLogMapper;
import net.octacomm.sample.dao.mapper.UserMapper;
import net.octacomm.sample.domain.Email;
import net.octacomm.sample.domain.EmailLogParam;
import net.octacomm.sample.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({ "/disability" })
@Controller
public class DisabilityStatusController extends AbstractCRUDController<EmailLogMapper, Email, EmailLogParam, Integer> {

	@Autowired
	private UserMapper UserMapper;

	@Autowired
	public void setCRUDMapper(EmailLogMapper mapper) {
		this.mapper = mapper;
	}

	protected Class<Email> getDomainClass() {
		return Email.class;
	}

	protected String getRedirectUrl(HttpSession session) {
		int id = ((Integer) session.getAttribute("id")).intValue();
		return "redirect:/disability/list?pIdx=" + id;
	}

	@ModelAttribute
	public void addAttributes(HttpSession session, Model model) {
		int id = 0;
		int role = 0;
		try {
			role = ((Integer) session.getAttribute("role")).intValue();
			id = ((Integer) session.getAttribute("id")).intValue();
		} catch (NullPointerException e) {
			id = 0;
			e.printStackTrace();
		}
		User user = this.UserMapper.get(Integer.valueOf(id));
		if (user != null) {
			model.addAttribute("role", Integer.valueOf(user.getRole()));
			model.addAttribute("pIdx", Integer.valueOf(user.getId()));
		}

	}
}