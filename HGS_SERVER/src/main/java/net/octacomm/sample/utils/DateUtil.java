package net.octacomm.sample.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil
{
  public static String getCurrentDatetime()
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
  }

  public static Date getCurrentDatetimeTypeOfDate() throws ParseException {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date now = new Date();
    Date today = format.parse(format.format(now));
    return today;
  }

  public static int DiffMin(String sDate1, String sDate2)
  {
    Date date1 = new Date(sDate1);
    Date date2 = new Date(sDate2);
    long mill = Math.abs(date1.getTime() - date2.getTime());
    long hours = TimeUnit.MILLISECONDS.toHours(mill);
    int min = (int)(TimeUnit.MILLISECONDS.toMinutes(mill) - TimeUnit.HOURS.toMinutes(hours));
    return min;
  }
}