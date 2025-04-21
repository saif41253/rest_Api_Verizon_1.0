package com.verizon.crm.api.generic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
/*
 * @author Saif
 */
public class JavaUtility {
	// add random no
	public int getRandomNumber() {
		Random random = new Random();
		int randomCount = random.nextInt();
		return randomCount;
	}
	// capturing system date
	public String getSystemDateYYYYMMDD() {
		 Date dateObj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateObj);
		return date;
	}
	// for require calendar date
	public String getRequiredDateYYYYMMDD(int days) {
		 Date dateObj = new Date();
		 SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		
		String date = sim.format(dateObj);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,days);
		String requireDate=sim.format(cal.getTime());
		return requireDate;
	}

}
