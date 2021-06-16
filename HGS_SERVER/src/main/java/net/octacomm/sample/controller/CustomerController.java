package net.octacomm.sample.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import net.octacomm.sample.dao.mapper.CustomerMapper;
import net.octacomm.sample.dao.mapper.StoreageMapper;
import net.octacomm.sample.dao.mapper.UserMapper;
import net.octacomm.sample.domain.Customer;
import net.octacomm.sample.domain.CustomerParam;
import net.octacomm.sample.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping({ "/customer" })
@Controller
public class CustomerController extends AbstractCRUDController<CustomerMapper, Customer, CustomerParam, Integer> {

	@Autowired
	private UserMapper UserMapper;

	@Autowired
	private StoreageMapper stMapper;

	@Autowired
	public void setCRUDMapper(CustomerMapper mapper) {
		this.mapper = mapper;
	}

	protected Class<Customer> getDomainClass() {
		return Customer.class;
	}

	protected String getRedirectUrl(HttpSession session) {
		int id = ((Integer) session.getAttribute("id")).intValue();
		return "redirect:/customer/list?pIdx=" + id;
	}

	@RequestMapping(value = { "/all/delete" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String allDelete(@RequestParam("id") int id, HttpSession session) {
		((CustomerMapper) this.mapper).delete(Integer.valueOf(id));
		this.stMapper.deleteParent(id);
		return getRedirectUrl(session);
	}

	@ResponseBody
	@RequestMapping(value = { "/get/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public List<Customer> logout(@RequestParam("pIdx") int pIdx) {
		return ((CustomerMapper) this.mapper).getAllList(pIdx);
	}

	@ModelAttribute
	public void addAttributes(HttpSession session, Model model) {
		int role = ((Integer) session.getAttribute("role")).intValue();
		int id = ((Integer) session.getAttribute("id")).intValue();
		User user = this.UserMapper.get(Integer.valueOf(id));
		model.addAttribute("role", Integer.valueOf(user.getRole()));
		model.addAttribute("pIdx", Integer.valueOf(user.getId()));
	}

	@ResponseBody
	@RequestMapping(value = { "/duplicate/check" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	private boolean customerDuplicateCheck(Customer domain) {
		return ((CustomerMapper) this.mapper).doCheckDuplication(domain) == null;
	}
}