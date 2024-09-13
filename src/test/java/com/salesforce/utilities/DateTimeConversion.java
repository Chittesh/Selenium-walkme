package com.salesforce.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeConversion {

	private DateTimeConversion() {

	}

	/**
	 * 
	 * @param daysOut - Number of days from current date to get date for
	 * @param format  - Desired format of date. Samples are "MM/dd/yyyy", "MMMM dd,
	 *                yyyy", and "dd-MM-yy"
	 * @return String future date in desired format
	 */
	public static String getDaysOut(String daysOut, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, Integer.parseInt(daysOut));
		String convertedDate = dateFormat.format(cal.getTime());
		return convertedDate;
	}

	public static String getSystemDate(String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		Calendar cal = Calendar.getInstance();
		String convertedDate = dateFormat.format(cal.getTime());
		return convertedDate;
	}
}