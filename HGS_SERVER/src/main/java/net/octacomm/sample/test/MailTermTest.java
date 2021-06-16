package net.octacomm.sample.test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MailTermTest {
	
	
	public static void main(String[] args) {
		
		Date date1 = new Date("Fri,  1 Nov 2019 15:39:32 +0900 (KST)");
		Date date2 = new Date("Thu, 24 Oct 2019 13:13:41 +0900 (KST)");
		
		long mill = Math.abs(date1.getTime() - date2.getTime());
        // 시로 변환 ( millisecond -> hour 로 변환 ) 
        long hours = TimeUnit.MILLISECONDS.toHours(mill);
		System.err.println("hours : " + hours);
		
		int min = (int) (TimeUnit.MILLISECONDS.toMinutes(mill) - TimeUnit.HOURS.toMinutes(hours));
		
		String diff = String.format("%d hour(s) %d min(s)", hours
                , TimeUnit.MILLISECONDS.toMinutes(mill) - TimeUnit.HOURS.toMinutes(hours));
        System.out.println(diff);
        System.err.println("min : " + min);
	}
}
