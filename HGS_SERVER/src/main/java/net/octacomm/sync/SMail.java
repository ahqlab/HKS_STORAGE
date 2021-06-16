package net.octacomm.sync;

import lombok.Data;

@Data
public class SMail
{
  private int id;
  private String status;
  private String eventName;
  private String storeageName;
  private String uId;
  private String ip;
  private String dateTime;
  private String mailFrom;
  private String mailDate;
  private String body;
  private int sendMMS;

  public static SMail getInstance()
  {
    return Holder.singleton;
  }
  
  public void addAllItem(String status, String subject, String mailFrom, String mailDate, String body)
  {
    this.status = status.trim();
    this.mailFrom = mailFrom.trim();
    this.mailDate = mailDate.trim();
    this.body = body.trim();
    addSubjectItems(subject);
  }

  public void addSubjectItems(String subject) {
    String[] array = subject.split(",");
    this.eventName = array[0].trim();
    this.storeageName = array[1].replace("Name:", "").trim();
    this.uId = array[2].trim();
    this.ip = array[3].replace("IP", "").trim();
    this.dateTime = array[4].replace("mail date/time: ", "").trim();
  }

  private static class Holder
  {
    static final SMail singleton = new SMail();
  }


}