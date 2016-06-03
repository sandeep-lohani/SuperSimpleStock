package com.jpm.sss.stockservice.utils;

import java.util.Calendar;
import java.util.Date;
/**
 * Utility class.
 * 
 * @author Sandeep Lohani
 *
 */
public class DatesUtils {
	/**
	 * Get date object after adding the number of minutes passed as parameter.
	 * 
	 * @param minutes.
	 * 
	 * @return Date.
	 */
	public Date getDateWithAddedMinutes(int minutes){
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, minutes);
		return now.getTime();
	}
}
