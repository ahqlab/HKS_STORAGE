package com.hankisul.storage.test;

import java.io.IOException;
import java.util.List;

import com.hankisul.storage.domain.Customer;
import com.hankisul.storage.domain.Email;
import com.hankisul.storage.domain.OverlabTerm;
import com.hankisul.storage.domain.ServerStatus;
import com.hankisul.storage.service.CustomerService;
import com.hankisul.storage.service.EmailLogService;
import com.hankisul.storage.service.MailReaderService;
import com.hankisul.storage.service.OverlabTermService;
import com.hankisul.storage.service.ServerStatusService;
import com.hankisul.storage.utils.BeanUtils;
import com.hankisul.storage.utils.DateUtil;
import com.hankisul.storage.view.SmsService;
import com.hankisul.sync.SMail;
import com.hankisul.sync.ServiceOperationCount;

import lombok.Getter;
import lombok.Setter;

public class MainJobThread implements Runnable {
	
	@Getter
	@Setter
	private int userIdx;
	
	@Getter
	@Setter
	private String serviceEmail;
	
	@Getter
	@Setter
	private String serviceEmailPassword;
	
	@Getter
	@Setter
	private boolean command;
	
	ServerStatusService serverStatusService;
	MailReaderService mailReaderService;
	EmailLogService emailLogService;
	CustomerService customerService;
	OverlabTermService overlabTermService;

	public MainJobThread(int userIdx, String serviceEmail, String serviceEmailPassword, boolean command) {
		this.userIdx = userIdx;
		this.serviceEmail = serviceEmail;
		this.serviceEmailPassword = serviceEmailPassword;
		this.command = command;
		
		serverStatusService = (ServerStatusService) BeanUtils.getBean("serverStatusService");
		mailReaderService = (MailReaderService) BeanUtils.getBean("mailReaderService");
		emailLogService =  (EmailLogService) BeanUtils.getBean("emailLogService");
		customerService = (CustomerService) BeanUtils.getBean("customerService");
		overlabTermService =  (OverlabTermService) BeanUtils.getBean("overlabTermService");
	}

	@Override
	public void run() {
		try {
			serverStatusService.insert(
					new ServerStatus(
							userIdx, 
							Integer.parseInt(String.valueOf(Thread.currentThread().getId())) , 
							command == true ? 1 : 0));
		}catch (Exception e) {
			e.getStackTrace();
		}
		
		while (serverStatusService.get(userIdx) != null ? true : false) {
			try {
				Thread.sleep(1000);
				doWork();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.err.println("userIdx : " + userIdx + " serviceEmail : " + serviceEmail +  " Thread.currentThread : " + Thread.currentThread().getId() );
		}

	}
	
	private void doWork() {
		//System.err.println("getThreadID : " + Thread.currentThread().getId()  + " getServiceEmail : " + getServiceEmail());
		//System.err.println("getServiceEmail() : " + getServiceEmail());
		//System.err.println("getServiceEmailPassword() : " + getServiceEmailPassword());
		
		List<Email> list = mailReaderService.getMails(getServiceEmail(), getServiceEmailPassword());
		System.err.println("list size : : " + list.size());
		if (list != null) {
			SMail sMail = SMail.getInstance();

			ServiceOperationCount count = ServiceOperationCount.getInstance();
			System.err.println("새로운 메일  " + list.size() + " 건이  토착했습니다.");
			System.err.println("마지막내용  " + sMail);
			System.err.println("number " + ServiceOperationCount.getNum());

			for (Email email : list) {
				Email beforeEmail = emailLogService.getListRow();
				boolean matchEmail = duplicationEmail(beforeEmail, email);
				// test@hankisul.com
				// Event notification, Name:RS-HGS 1012S, UID 89776, IP 10.10.100.1, mail
				// date/time: 2020/02/25 AM 12:50:00
				emailLogService.insert(email);
				if ((email.getStatus().equals("Critical")) || (email.getStatus().equals("Critical Error"))) {
					sendSMS(email);
				} else {
					OverlabTerm term = overlabTermService.get(Integer.valueOf(getUserIdx()));

					int diff = DateUtil.DiffMin(email.getMailDate(), sMail.getMailDate());
					if (email.getStatus().equals("Information")) {
						if (matchEmail) {
							if ((diff > term.getInfo()) || (term.getInfo() == 0)) {
								sendSMS(email);
							}
						} else {
							sendSMS(email);
						}
					} else if (email.getStatus().equals("Warning")) {
						if (matchEmail) {
							if ((diff > term.getWarning()) || (term.getWarning() == 0)) {
								sendSMS(email);
							}
						} else {
							sendSMS(email);
						}
							
					} else if (email.getStatus().equals("Error"))
						if (matchEmail) {
							if ((diff > term.getError()) || (term.getError() == 0)) {
								sendSMS(email);
							}
						} else {
							sendSMS(email);
						}
							
				}
			}
		}
	}
	
	private void sendSMS(Email email) {
		Customer customer = customerService.getMagagerHP(email.getStoreageName(), email.getIp());
		if (customer.getManagerHP() != null) {
			System.err.println("문자를 보냅니다. " + customer.getManagerHP());
			if (customer.getSms() > 0) {
				emailLogService.checkSendMMS(email.getId());
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
		//String bodyMsg1 = beforeEmail.getBody().split(",")[1].trim();
		//String bodyMsg2 = email.getBody().split(",")[1].trim();
		// return bodyMsg1.equals(bodyMsg2);
		// object를 비교하게 변경함. 2020.10.04
		return beforeEmail.equals(email);
	}
}
