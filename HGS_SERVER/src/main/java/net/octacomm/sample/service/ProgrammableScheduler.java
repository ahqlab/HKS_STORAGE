package net.octacomm.sample.service;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

import javax.mail.Session;

import net.octacomm.sample.dao.mapper.CustomerMapper;
import net.octacomm.sample.dao.mapper.EmailLogMapper;
import net.octacomm.sample.dao.mapper.OverlabTermMapper;
import net.octacomm.sample.dao.mapper.ServerStatusMapper;
import net.octacomm.sample.dao.mapper.UserMapper;
import net.octacomm.sample.domain.Customer;
import net.octacomm.sample.domain.Email;
import net.octacomm.sample.domain.OverlabTerm;
import net.octacomm.sample.domain.ServerStatus;
import net.octacomm.sample.test.MainJobThread;
import net.octacomm.sample.utils.DateUtil;
import net.octacomm.sample.view.SmsService;
import net.octacomm.sync.SMail;
import net.octacomm.sync.ServiceOperationCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Component
public class ProgrammableScheduler {
	
	public ProgrammableScheduler() {}
	
	private String serviceEmail;
	
	private String serviceEmailPassword;
	
	@Setter
	@Getter
	private int userIdx;
	
	@Setter
	@Getter
	private boolean command;
	
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
	private ServerStatusMapper serverStatusMapper;
	
	
	private ThreadPoolTaskScheduler scheduler;

	public boolean getSchedulerStatus() {
		try {
			return serverStatusMapper.get(getUserIdx()).getStatus() == 0 ? false : true;
		}catch (NullPointerException e) {
			return false;
		}
		
	}
	
	public void stopScheduler() {
		serverStatusMapper.delete(getUserIdx());
	}
	
	
	public void startScheduler (final int userIdx, String serviceEmail, String serviceEmailPassword, boolean command) {
		this.serviceEmail = serviceEmail;
		this.serviceEmailPassword = serviceEmailPassword;
		this.userIdx = userIdx;
		this.command = command;
		
		
		if(command) {
			MainJobThread mainJob = new MainJobThread(userIdx, serviceEmail, serviceEmailPassword, command);
			Thread td = new Thread(mainJob);
			td.start();
		}
		
		
		//this.scheduler = new ThreadPoolTaskScheduler();
		//this.scheduler.initialize();
		//this.scheduler.schedule(getRunnable(), getTrigger());
			
//		final ServerStatus status = serverStatusMapper.get(getUserIdx());
//		System.err.println("status 1 : " + status + "command : " + command);
//		Thread thread = new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				boolean re = String.valueOf(serverStatusMapper.get(userIdx).getThreadIdx()).equals(Thread.currentThread().getId());
//				System.err.println("re : " + Thread.currentThread().getId() + " re2 : " + String.valueOf(serverStatusMapper.get(userIdx).getThreadIdx()));
//				while (String.valueOf(serverStatusMapper.get(userIdx).getThreadIdx()).matches(String.valueOf(Thread.currentThread().getId()))) {
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					System.err.println("do Someting : " + Thread.currentThread().getId());
//				}
//			}
//		});
		//thread.start();
//		if(status == null) {
//			if(!command) {
//				System.err.println("쓰레드가 꺼져있습니다.");
//			}else {
//				System.err.println("THREAD Start : " + String.valueOf(thread.getId()));
//				serverStatusMapper.insert(new ServerStatus(ProgrammableScheduler.this.getUserIdx(), Integer.parseInt(String.valueOf(thread.getId())) , 1));
//				thread.start();
//			}
//		}else {
//			if(command) {
//				System.err.println("리프레쉬됨");
//			}else {
//				System.err.println("THREAD Stop : ");
//				serverStatusMapper.deleteOne(getUserIdx());
//				//thread.stop();
//			}
//		}
		
		
		
		
//		if(status == null) {
//			if(command) {
//				
//				new Thread(new Runnable() {
//					
//					@Override
//					public void run() {
//						
//						serverStatusMapper.insert(new ServerStatus(ProgrammableScheduler.this.getUserIdx(), Integer.parseInt(String.valueOf(Thread.currentThread().getId())) , 1));
//						
//						while (true) {
//							try {
//								Thread.sleep(1000);
//							} catch (InterruptedException e) {
//								e.printStackTrace();
//							}
//							// do Someting
//							System.err.println("do Someting : " + Thread.currentThread().getId());
//						}
//						
//						
//						
//							//System.err.println("status 2 : getUserIdx() : " + ProgrammableScheduler.this.getUserIdx());
//							
//							//ServerStatus obj = new ServerStatus(ProgrammableScheduler.this.getUserIdx(), Integer.parseInt(String.valueOf(Thread.currentThread().getId())) , 1);
//							//serverStatusMapper.insert(new ServerStatus(ProgrammableScheduler.this.getUserIdx(), Integer.parseInt(String.valueOf(Thread.currentThread().getId())) , 1));
//							//while (serverStatusMapper.getStatus(ProgrammableScheduler.this.getUserIdx(), Integer.parseInt(String.valueOf(Thread.currentThread().getId()))) != null ? true : false) {
//							//	try {
//							//		Thread.sleep(1000);
//							//	} catch (InterruptedException e) {e.printStackTrace();}
//							//	//do Someting
//							//	System.err.println("do Someting : " + Thread.currentThread().getId());
//							//}
//						
//					}}).start();
//			
//				
//			}
//		}else {
//			try {
//				serverStatusMapper.deleteOne(getUserIdx());
//			}catch (Exception e) {
//				// TODO: handle exception
//			}
//			
//		}
		
		
		//if (getSchedulerStatus()) {
		//	this.scheduler = new ThreadPoolTaskScheduler();
		//	this.scheduler.initialize();
		//	this.scheduler.schedule(getRunnable(), getTrigger());
		//} else {
		//	////System.err.println("isShutdown() : " + st.isShutdown() + "isTerminated :  " + st.isTerminated());
		//	scheduler.shutdown();
		//	
		//	//if (st.isShutdown()) {
		//		//System.err.println("다시 돌꺼야");
		//		Runnable td = getRunnable();
		//		this.scheduler = new ThreadPoolTaskScheduler();
		//		//this.scheduler.setPoolSize(3);
		//		this.scheduler.initialize();
		//		this.scheduler.schedule(getRunnable(), getTrigger());
		//	}
		//}
	}

	

	private Runnable getRunnable() {
		return new Runnable() {
			public void run() {
				System.err.println("getThreadID : " + Thread.currentThread().getId()  + " getServiceEmail : " + ProgrammableScheduler.this.getServiceEmail());
				////System.err.println("getServiceEmail() : " + ProgrammableScheduler.this.getServiceEmail());
				////System.err.println("getServiceEmailPassword() : " + ProgrammableScheduler.this.getServiceEmailPassword());
				List<Email> list = ProgrammableScheduler.this.mailReaderService.getMails(
						ProgrammableScheduler.this.getServiceEmail(),
						ProgrammableScheduler.this.getServiceEmailPassword());

				if (list != null) {
					SMail sMail = SMail.getInstance();

					ServiceOperationCount count = ServiceOperationCount.getInstance();
					//System.err.println("새로운 메일  " + list.size() + " 건이  토착했습니다.");
					//System.err.println("마지막내용  " + sMail);
					//System.err.println("number " + ServiceOperationCount.getNum());

					for (Email email : list) {
						Email beforeEmail = ProgrammableScheduler.this.emailLogMapper.getListRow();
						boolean matchEmail = ProgrammableScheduler.this.duplicationEmail(beforeEmail, email);
						// test@hankisul.com
						// Event notification, Name:RS-HGS 1012S, UID 89776, IP 10.10.100.1, mail
						// date/time: 2020/02/25 AM 12:50:00
						ProgrammableScheduler.this.emailLogMapper.insert(email);
						if ((email.getStatus().equals("Critical")) || (email.getStatus().equals("Critical Error"))) {
							ProgrammableScheduler.this.sendSMS(email);
						} else {
							OverlabTerm term = ProgrammableScheduler.this.overlabTermMapper.get(Integer.valueOf(1));

							int diff = DateUtil.DiffMin(email.getMailDate(), sMail.getMailDate());
							if (email.getStatus().equals("Information")) {
								if (matchEmail) {
									if ((diff > term.getInfo()) || (term.getInfo() == 0)) {
										ProgrammableScheduler.this.sendSMS(email);
									}
								} else
									ProgrammableScheduler.this.sendSMS(email);
							} else if (email.getStatus().equals("Warning")) {
								if (matchEmail) {
									if ((diff > term.getWarning()) || (term.getWarning() == 0))
										ProgrammableScheduler.this.sendSMS(email);
								} else
									ProgrammableScheduler.this.sendSMS(email);
							} else if (email.getStatus().equals("Error"))
								if (matchEmail) {
									if ((diff > term.getError()) || (term.getError() == 0))
										ProgrammableScheduler.this.sendSMS(email);
								} else
									ProgrammableScheduler.this.sendSMS(email);
						}
					}
				}
			}
		};
	}

	private void sendSMS(Email email) {
		Customer customer = this.customerMapper.getMagagerHP(email.getStoreageName(), email.getIp());
		if (customer.getManagerHP() != null) {
			//System.err.println("문자를 보냅니다. " + customer.getManagerHP());
			if (customer.getSms() > 0) {
				this.emailLogMapper.checkSendMMS(email.getId());
				try {
					new SmsService().send2(email, customer.getManagerHP().trim(), customer.getSendNumber());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			//System.err.println("담당자를 찾을 수 없습니다.");
		}
	}

	private boolean duplicationEmail(Email beforeEmail, Email email) {
		String bodyMsg1 = beforeEmail.getBody().split(",")[1].trim();
		String bodyMsg2 = email.getBody().split(",")[1].trim();
		// return bodyMsg1.equals(bodyMsg2);
		// object를 비교하게 변경함. 2020.10.04
		return beforeEmail.equals(email);
	}

	private void beforeMessageSend(List<Email> list) {
		for (int j = 0; j < list.size(); j++) {
			this.emailLogMapper.insert((Email) list.get(j));

			if (j == list.size() - 1) {
				sendSMS((Email) list.get(j));
			}
		}
	}

	private Trigger getTrigger() {
		return new PeriodicTrigger(10000L);
	}
	
	
	
	
	public String getServiceEmail() {
		return this.serviceEmail;
	}

	public void setServiceEmail(String serviceEmail) {
		this.serviceEmail = serviceEmail;
	}

	public String getServiceEmailPassword() {
		return this.serviceEmailPassword;
	}

	public void setServiceEmailPassword(String serviceEmailPassword) {
		this.serviceEmailPassword = serviceEmailPassword;
	}

}