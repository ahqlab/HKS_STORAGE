package com.hankisul.storage.controller;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hankisul.storage.dao.mapper.CustomerMapper;
import com.hankisul.storage.dao.mapper.EmailLogMapper;
import com.hankisul.storage.dao.mapper.OverlabTermMapper;
import com.hankisul.storage.dao.mapper.StoreageMapper;
import com.hankisul.storage.dao.mapper.UserMapper;
import com.hankisul.storage.domain.Customer;
import com.hankisul.storage.domain.Email;
import com.hankisul.storage.domain.OverlabTerm;
import com.hankisul.storage.domain.Storeage;
import com.hankisul.storage.domain.StoreageParam;
import com.hankisul.storage.domain.User;
import com.hankisul.storage.service.MailReaderService;
import com.hankisul.storage.service.ProgrammableScheduler;
import com.hankisul.storage.utils.DateUtil;
import com.hankisul.storage.view.SmsService;
import com.hankisul.sync.SMail;
import com.hankisul.sync.ServiceOperationCount;

@RequestMapping({ "/storeage" })
@Controller
public class StoreageController extends AbstractCRUDController<StoreageMapper, Storeage, StoreageParam, Integer> {

	@Autowired
	ProgrammableScheduler scheduler;

	@Autowired
	private UserMapper UserMapper;

	@Autowired
	private EmailLogMapper emailLogMapper;

	@Autowired
	private OverlabTermMapper overlabTermMapper;

	@Autowired
	private MailReaderService mailReaderService;

	@Autowired
	private CustomerMapper customerMapper;

	@Autowired
	public void setCRUDMapper(StoreageMapper mapper) {
		this.mapper = mapper;
	}

	protected Class<Storeage> getDomainClass() {
		return Storeage.class;
	}

	protected String getRedirectUrl(HttpSession session) {
		int id = ((Integer) session.getAttribute("id")).intValue();
		return "redirect:/storeage/list?pIdx=" + id;
	}

	@ResponseBody
	@RequestMapping(value = { "/change/sms/status" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public boolean changeSmsStatus(Storeage domain) {
		System.err.println("storeage : " + domain);
		return ((StoreageMapper) this.mapper).changeSmsStatus(domain) != 0;
	}

	@RequestMapping(value = { "/regist2" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	private void regist2(Storeage domain) {
		System.err.println("storeage : " + domain);
	}

	@ResponseBody
	@RequestMapping(value = { "/duplicate/check" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	private boolean storeageDuplicateCheck(Storeage domain) {
		return ((StoreageMapper) this.mapper).doCheckDuplication(domain) == null;
	}

	@RequestMapping(value = { "/test/sms" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	private void sendSms() {
		// this.scheduler.startScheduler("123", "324");
	}

	@ModelAttribute
	public void addAttributes(HttpSession session, Model model) {
		int role = ((Integer) session.getAttribute("role")).intValue();
		int id = ((Integer) session.getAttribute("id")).intValue();
		User user = this.UserMapper.get(Integer.valueOf(id));
		model.addAttribute("role", Integer.valueOf(user.getRole()));
		model.addAttribute("pIdx", Integer.valueOf(user.getId()));
	}

	public void getMailSync() {
		List<Email> list = this.mailReaderService.getMails("test@hankisul.com", "test4465");
		SMail sMail = SMail.getInstance();

		ServiceOperationCount count = ServiceOperationCount.getInstance();
		System.err.println("새로운 메일  " + list.size() + " 건이  토착했습니다.");
		System.err.println("마지막내용  " + sMail);
		System.err.println("number " + ServiceOperationCount.getNum());

		if (ServiceOperationCount.getNum() == 1) {
			beforeMessageSend(list);
			System.err.println("첫번째");
		} else {
			for (Email email : list) {
				Email beforeEmail = this.emailLogMapper.getListRow();
				boolean matchEmail = duplicationEmail(beforeEmail, email);

				this.emailLogMapper.insert(email);
				if ((email.getStatus().equals("Critical")) || (email.getStatus().equals("Critical Error"))) {
					sendSMS(email);
				} else {
					OverlabTerm term = this.overlabTermMapper.get(Integer.valueOf(1));

					int diff = DateUtil.DiffMin(email.getMailDate(), sMail.getMailDate());
					if (email.getStatus().equals("Information")) {
						if (matchEmail) {
							if ((diff > term.getInfo()) || (term.getInfo() == 0)) {
								sendSMS(email);
							}
						} else
							sendSMS(email);
					} else if (email.getStatus().equals("Warning")) {
						if (matchEmail) {
							if ((diff > term.getWarning()) || (term.getWarning() == 0))
								sendSMS(email);
						} else
							sendSMS(email);
					} else if (email.getStatus().equals("Error"))
						if (matchEmail) {
							if ((diff > term.getError()) || (term.getError() == 0))
								sendSMS(email);
						} else
							sendSMS(email);
				}
			}
		}
	}

	private void sendSMS(Email email) {
		Customer customer = this.customerMapper.getMagagerHP(email.getStoreageName(), email.getIp());
		if (customer.getManagerHP() != null) {
			System.err.println("문자를 보냅니다. " + customer.getManagerHP());
			if (customer.getSms() > 0) {
				this.emailLogMapper.checkSendMMS(email.getId());
				try {
					new SmsService().send2(email, customer.getManagerHP().trim(), customer.getSendNumber());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.err.println("담당자를 찾을 수 없습니다.");
		}
	}

	private boolean duplicationEmail(Email beforeEmail, Email email) {
		String bodyMsg1 = beforeEmail.getBody().split(",")[1].trim();
		String bodyMsg2 = email.getBody().split(",")[1].trim();

		return bodyMsg1.equals(bodyMsg2);
	}

	private void beforeMessageSend(List<Email> list) {
		for (int j = 0; j < list.size(); j++) {
			this.emailLogMapper.insert((Email) list.get(j));

			if (j == list.size() - 1) {
				sendSMS((Email) list.get(j));
			}
		}
	}

	@Scheduled(cron = "0 0 05 * * ?")
	public void resetSMStatus() {
		((StoreageMapper) this.mapper).resetSmsStatus();
	}
}