package net.octacomm.sample.controller;

import java.io.PrintStream;
import javax.servlet.http.HttpSession;
import net.octacomm.sample.dao.mapper.UserMapper;
import net.octacomm.sample.domain.ServerResponse;
import net.octacomm.sample.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping({ "/user" })
@Controller
public class UserController {

	@Autowired
	private UserMapper userMapper;

	@RequestMapping(value = { "/change/account" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public void send(Model model, HttpSession session) {
		int id = ((Integer) session.getAttribute("id")).intValue();
		//System.err.println("id : " + id);
		//System.err.println(" : " + this.userMapper.get(Integer.valueOf(id)));
		model.addAttribute("domain", this.userMapper.get(Integer.valueOf(id)));
	}

	@ResponseBody
	@RequestMapping(value = { "/change/info" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ServerResponse changeInfo(User pUser, HttpSession session) {
		int id = ((Integer) session.getAttribute("id")).intValue();
		pUser.setId(id);
		User user = this.userMapper.get(Integer.valueOf(id));
		if (user.getPassword() == pUser.getBeforePassword()) {
			return new ServerResponse("새로운 비밀번호를 입력하세요.", 0);
		}
		this.userMapper.changeUserPassword(pUser);
		return new ServerResponse("비밀번호가 변경되었습니다.", 1);
	}

	@ModelAttribute
	public void addAttributes(HttpSession session, Model model) {
		int role = ((Integer) session.getAttribute("role")).intValue();
		int id = ((Integer) session.getAttribute("id")).intValue();
		User user = this.userMapper.get(Integer.valueOf(id));
		model.addAttribute("role", Integer.valueOf(user.getRole()));
		model.addAttribute("pIdx", Integer.valueOf(user.getId()));
	}
}