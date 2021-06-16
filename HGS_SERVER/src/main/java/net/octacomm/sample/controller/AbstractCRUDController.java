package net.octacomm.sample.controller;

import java.io.PrintStream;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.octacomm.logger.Log;
import net.octacomm.sample.dao.CRUDMapper;
import net.octacomm.sample.domain.Domain;
import net.octacomm.sample.domain.DomainParam;
import net.octacomm.sample.utils.Pagination;
import org.slf4j.Logger;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SessionAttributes({ "domain" })
public abstract class AbstractCRUDController<M extends CRUDMapper<D, P, PK>, D extends Domain, P extends DomainParam, PK> {

	@Log
	private Logger logger;
	protected static final String URL_LIST = "/list";
	protected static final String URL_REGIST = "/regist";
	protected static final String URL_DETAIL = "/detail";
	protected static final String URL_UPDATE = "/update";
	protected static final String URL_DELETE = "/delete";
	protected M mapper;

	public abstract void setCRUDMapper(M paramM);

	@RequestMapping({ "/list" })
	public void list(Model model, @ModelAttribute("domainParam") P param, BindingResult result, HttpSession session) {
		System.out.println("Search Param : {}" + param);

		int totalCount = this.mapper.getCountByParam(param);

		System.out.println("Total Count : {}" + totalCount);
		Pagination page;
		// Pagination page;
		if ((param.getPageSize() > 0) && (param.getPageGroupSize() > 0))
			page = new Pagination(param.getPageSize(), param.getPageGroupSize(), totalCount, param.getCurrentPage());
		else {
			page = new Pagination(totalCount, param.getCurrentPage());
		}

		List domainList = this.mapper.getListByParam(page.getStartRow(), page.getPageSize(), param);

		System.out.println("Domain List Size : {}" + domainList.size());

		model.addAttribute("page", page);
		model.addAttribute("domainList", domainList);
	}

	@RequestMapping(value = { "/regist" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public void regist(Model model, HttpSession session) throws InstantiationException, IllegalAccessException {
		model.addAttribute("domain", getDomainClass().newInstance());
	}

	protected abstract Class<D> getDomainClass();

	@RequestMapping(value = { "/regist" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String regist(@ModelAttribute("domain") D domain, SessionStatus sessionStatus, HttpSession session) {
		System.err.println("domain : " + domain);

		if (this.mapper.insert(domain) == 1) {
			sessionStatus.setComplete();
			return getRedirectUrl(session);
		}
		return "/regist";
	}

	protected abstract String getRedirectUrl(HttpSession paramHttpSession);

	@RequestMapping(value = { "/update", "/detail" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public void form(Model model, @RequestParam PK id, HttpSession session) {
		model.addAttribute("domain", this.mapper.get(id));
	}

	@RequestMapping(value = { "/update" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String update(@ModelAttribute("domain") D domain, RedirectAttributes redirectAttributes,
			HttpSession session) {
		this.logger.debug("domain : {}", domain);

		if (this.mapper.update(domain) == 1) {
			return getRedirectUrl(session);
		}
		return "/update";
	}

	@RequestMapping(value = { "/delete" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String delete(@RequestParam PK id, RedirectAttributes redirectAttributes, HttpSession session) {
		if (this.mapper.delete(id) == 1) {
			return getRedirectUrl(session);
		}
		throw new RuntimeException("삭제중 오류");
	}
}