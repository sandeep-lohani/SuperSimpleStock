/**
 * 
 */
package com.jpm.sss.stockservice.utils;

import java.text.DecimalFormat;
/**
 * Utility class.
 * @author Sandeep Lohani
 *
 */
public class PrimitivesUtils {
	
	/**
	 * Get the double value upto two decimal places
	 * @param number
	 * @return
	 */
	public static double roundTwoDecimalPlaces(double number){
	    DecimalFormat twoDForm = new DecimalFormat("#.00");
	    return Double.parseDouble(twoDForm.format(number));
	}
}
