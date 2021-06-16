package net.octacomm.sample.utils;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MailReader
{
  private static final String MAIL_POP_HOST = "wmbox2.ecounterp.com";
  private static final String MAIL_STORE_TYPE = "pop3";
  private static final String POP_USER = "test@hankisul.com";
  private static final String POP_PASSWORD = "test4465";
  private static final String POP_PORT = "110";

  public static void getMails(String user, String password)
  {
    List list = new ArrayList();
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

      for (int i = messages.length - 1; i > -1; i--) {
        Message message = messages[i];

        System.out.println("---------------------------------");
        System.out.println("Email Number " + (i + 1));
        System.out.println("Subject: " + message.getSubject());
        System.out.println("From: " + message.getFrom()[0]);
        System.out.println("Date: " + message.getHeader("Date")[0]);
        System.out.println("Body: " + getTextFromMessage(message));
      }

      emailFolder.close(false);
      store.close();
    }
    catch (NoSuchProviderException e) {
      e.printStackTrace();
    } catch (MessagingException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String getTextFromMessage(Message message)
    throws MessagingException, IOException
  {
    String result = "";
    if (message.isMimeType("text/plain")) {
      result = message.getContent().toString();
    } else if (message.isMimeType("multipart/*")) {
      MimeMultipart mimeMultipart = (MimeMultipart)message.getContent();
      result = getTextFromMimeMultipart(mimeMultipart);
    }

    return result;
  }

  public static String getTextFromMimeMultipart(MimeMultipart mimeMultipart)
    throws MessagingException, IOException
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

  public static void main(String[] args)
  {
    getMails("test@hankisul.com", "test4465");
  }
}