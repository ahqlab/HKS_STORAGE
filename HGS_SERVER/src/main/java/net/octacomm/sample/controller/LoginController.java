package net.octacomm.sample.controller;

import java.io.PrintStream;
import javax.servlet.http.HttpSession;
import net.octacomm.sample.domain.User;
import net.octacomm.sample.exceptions.InvalidPasswordException;
import net.octacomm.sample.exceptions.NotFoundUserException;
import net.octacomm.sample.service.LoginService;
import net.octacomm.sample.service.ProgrammableScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	public static final String LOGIN_URL = "/login";
	public static final String DEFAULT_TARGET_URL = "/disability/list";
	public static final String ADMIN_DEFAULT_TARGET_URL = "/admin/user/list";

	@Autowired
	ProgrammableScheduler scheduler;

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = { "", "/" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String index(Model model) {
		model.addAttribute("domain", new User());
		return "index";
	}

	@RequestMapping(value = { "/login" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String login(Model model) {
		model.addAttribute("adminUser", new User());
		return "/login";
	}

	@RequestMapping(value = { "/login" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String login(Model model, HttpSession session, User user, Errors errors) {
		try {
			User result = this.loginService.login(user);
			System.err.println("result : " + result);
			session.setAttribute("id", Integer.valueOf(result.getId()));
			session.setAttribute("userId", result.getUserId());
			session.setAttribute("role", Integer.valueOf(result.getRole()));
			if (result.getRole() > 0) {
				return "redirect:/disability/list?pIdx=" + result.getId();
			}
			return "redirect:/admin/user/list";
		} catch (NotFoundUserException nfe) {
			model.addAttribute("errorMessage", "아이디가 존재하지 않습니다.");
			errors.reject("test", "아이디가 존재하지 않습니다.");
		} catch (InvalidPasswordException ipe) {
			model.addAttribute("errorMessage", "비밀번호가 일치하지 않습니다.");
			errors.reject("test", "비밀번호가 일치하지 않습니다.");
		}
		model.addAttribute("domain", new User());
		return "index";
	}

	@RequestMapping(value = { "/logout" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String logout(HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:/";
	}
}