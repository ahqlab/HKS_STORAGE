package net.octacomm.sample.service;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import net.octacomm.sample.dao.mapper.EmailLogMapper;
import net.octacomm.sample.domain.Email;
import net.octacomm.sample.utils.CommonUtil;
import net.octacomm.sync.SMail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service("mailReaderService")
public class MailReaderServiceImpl implements MailReaderService
{

  @Autowired
  EmailLogMapper emailLogMapper;
  
  private static final String MAIL_POP_HOST = "wmbox2.ecounterp.com";
  private static final String MAIL_STORE_TYPE = "pop3";
  private static final String POP_USER = "test@hankisul.com";
  private static final String POP_PASSWORD = "test4465";
  private static final String POP_PORT = "110";

  public List<Email> getMails(String user, String password)
  {
	  
    List<Email> emails = new ArrayList<Email>();
    SMail sMail = SMail.getInstance();
    
    try
    {
      Properties properties = new Properties();
      properties.put("mail.pop3.host", "wmbox2.ecounterp.com");
      properties.put("mail.pop3.port", "110");
      properties.put("mail.pop3.starttls.enable", "true");
      properties.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

      Session session = Session.getInstance(System.getProperties(), null);

      Store store = session.getStore("pop3");
      store.connect("wmbox2.ecounterp.com", user, password);

      Folder emailFolder = store.getFolder("INBOX");
      emailFolder.open(1);

      Message[] messages = emailFolder.getMessages();
      System.out.println("messages.length---" + messages.length);
      System.err.println("last message : " + getTextFromMessage(messages[(messages.length - 1)]));

      Email mail = null;
      Message message = messages[(messages.length - 1)];
      
      if(!CommonUtil.checkString(getTextFromMessage(message))) {
    	  if (getTextFromMessage(message).contains("Information")) {
        	  mail = new Email("Information", message.getSubject(), message.getFrom()[0].toString(), message.getHeader("Date")[0].toString(), getTextFromMessage(message));
        	  sMail.addAllItem("Information", message.getSubject(), message.getFrom()[0].toString(), message.getHeader("Date")[0].toString(), getTextFromMessage(message));
          } else if (getTextFromMessage(message).contains("Warning")) {
        	  String[] array = message.getSubject().split(",");
    	      if (array.length > 2) {
    	          mail = new Email("Warning", message.getSubject(), message.getFrom()[0].toString(), message.getHeader("Date")[0].toString(), getTextFromMessage(message));
    	          sMail.addAllItem("Warning", message.getSubject(), message.getFrom()[0].toString(), message.getHeader("Date")[0].toString(), getTextFromMessage(message));
    	      }
          
          } else if (getTextFromMessage(message).contains("Error")) {
        	  mail = new Email("Error", message.getSubject(), message.getFrom()[0].toString(), message.getHeader("Date")[0].toString(), getTextFromMessage(message));
        	  sMail.addAllItem("Error", message.getSubject(), message.getFrom()[0].toString(), message.getHeader("Date")[0].toString(), getTextFromMessage(message));
          } else if (getTextFromMessage(message).contains("Critical Error")) {
        	  mail = new Email("Critical Error", message.getSubject(), message.getFrom()[0].toString(), message.getHeader("Date")[0].toString(), getTextFromMessage(message));
        	  sMail.addAllItem("Critical Error", message.getSubject(), message.getFrom()[0].toString(), message.getHeader("Date")[0].toString(), getTextFromMessage(message));
          } else if (getTextFromMessage(message).contains("Critical")) {
        	  mail = new Email("Critical", message.getSubject(), message.getFrom()[0].toString(), message.getHeader("Date")[0].toString(), getTextFromMessage(message));
        	  sMail.addAllItem("Critical", message.getSubject(), message.getFrom()[0].toString(), message.getHeader("Date")[0].toString(), getTextFromMessage(message));
          }

          if (mail != null)
          {
        	  List<Email>  email = this.emailLogMapper.existDuplicateEmail(mail);
        	  if (email.size() == 0) {
        		  emails.add(mail);
        	  }
          }
      }
      emailFolder.close(false);
      store.close();
    } catch (AuthenticationFailedException e) {
      return null;
    } catch (NoSuchProviderException e) {
      return null;
    } catch (MessagingException e) {
      return null;
    } catch (Exception e) {
      return null;
    }
    return emails;
  }

  public static String getTextFromMessage(Message message) throws MessagingException, IOException {
    String result = "";
    if (message.isMimeType("text/plain")) {
      result = message.getContent().toString();
    } else if (message.isMimeType("multipart/*")) {
      MimeMultipart mimeMultipart = (MimeMultipart)message.getContent();
      result = getTextFromMimeMultipart(mimeMultipart);
    }
    return result;
  }

  public static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException
  {
    String result = "";
    int count = mimeMultipart.getCount();

    for (int i = 0; i < count; i++) {
      BodyPart bodyPart = mimeMultipart.getBodyPart(i);
      if (bodyPart.isMimeType("text/plain")) {
        result = result + "\n" + bodyPart.getContent();
        break;
      }if (bodyPart.isMimeType("text/html")) {
        String html = (String)bodyPart.getContent();
        result = result + "\n" + Jsoup.parse(html).text();
      } else if ((bodyPart.getContent() instanceof MimeMultipart)) {
        result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
      }
    }
    return result;
  }
}