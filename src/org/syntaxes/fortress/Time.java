/*
Fortress Telnet Server
Copyright (c) ABrambleNinja, fortress.syntaxes.org

This file is part of the Fortress Telnet Server.

  The Fortress Telnet Server is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  The Fortress Telnet Server is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with The Fortress Telnet Server.  If not, see <http://www.gnu.org/licenses/>.
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
