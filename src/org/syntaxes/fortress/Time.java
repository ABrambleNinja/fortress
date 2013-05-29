/*
Fortress Telnet Server
Copyright (c) ABrambleNinja, fortress.syntaxes.org

Released under the MIT license <http://abrambleninja.mit-license.org>
*/
package org.syntaxes.fortress;

import java.util.Calendar;
import java.text.SimpleDateFormat;
/**
 * Contains time-related functions
 * @author ABrambleNinja
 *
 */
public class Time {

	/**
	 * A function that returns the current time
	 * @param format a String that contains the desired format for the time (i.e. "EEEE, MMMM d, yyyy 'at' h:mm a.")
	 * @return current time
	 */
	public static String now(String format) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat now = new SimpleDateFormat(format);
		return now.format(calendar.getTime());
	}
	/**
	 * A function that returns the current time.
	 * @return current time in the format "EEEE, MMMM d, yyyy 'at' h:mm a."
	 */
	public static String now() {
		return now("EEEE, MMMM d, yyyy 'at' h:mm a.");

	}

}
