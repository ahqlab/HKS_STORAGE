package net.octacomm.sample.controller.admin;

import javax.servlet.http.HttpSession;
import net.octacomm.sample.controller.AbstractCRUDController;
import net.octacomm.sample.dao.mapper.OverlabTermMapper;
import net.octacomm.sample.dao.mapper.UserMapper;
import net.octacomm.sample.domain.OverlabTerm;
import net.octacomm.sample.domain.User;
import net.octacomm.sample.domain.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

@RequestMapping({ "/admin/user" })
@Controller
public class AdminUserController extends AbstractCRUDController<UserMapper, User, UserParam, Integer> {

	@Autowired
	private OverlabTermMapper overlabTermMapper;

	@Autowired
	public void setCRUDMapper(UserMapper mapper) {
		this.mapper = mapper;
	}

	protected Class<User> getDomainClass() {
		return User.class;
	}

	protected String getRedirectUrl(HttpSession session) {
		return "redirect:/admin/user/list";
	}

	@RequestMapping(value = { "/regist2" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String regist(@ModelAttribute("domain") User domain, SessionStatus sessionStatus, HttpSession session) {
		int id = ((Integer) session.getAttribute("id")).intValue();
		if (((UserMapper) this.mapper).regist(domain) == 1) {
			this.overlabTermMapper.insert(new OverlabTerm(0, 0, 0, domain.getId()));
			sessionStatus.setComplete();
			return getRedirectUrl(session);
		}
		return "/regist";
	}

	@ResponseBody
	@RequestMapping(value = { "/change/account" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public boolean changeAdminUserPassword(HttpSession session, @RequestParam("id") int id, @RequestParam("password") String password) {
		String userId = (String) session.getAttribute("userId");
		int result = ((UserMapper) this.mapper).changeAdminUserPassword(id, password);
		if (result > 0) {
			return true;
		}
		return false;
	}
}