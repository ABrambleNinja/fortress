/*
Fortress Telnet Server
Copyright (c) ABrambleNinja, fortress.syntaxes.org

Released under the MIT license <http://abrambleninja.mit-license.org>
*/
/*
Small Telnet Chatserver
Copyright (C) 2005 Wiesner Thomas

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
package org.syntaxes.fortress;
/**
 * Contains all the ANSI color codes, stored in variables.
 * Forked from Small Telnet Chatserver by Wiesner Thomas, and then modified by ABrambleNinja.
 * Javadocs added by ABrambleNinja
 * @author ABrambleNinja and Wiesner Thomas
 *
 */
public class Colors {
	/**
	 * Escape character for the colors. Base of all colors.
	 */
	public static final String esc = ((char) 27) + "";

	/**
	 * The ANSI color black.
	 */
	public static final String black = esc + "[0;30m";
	
	/**
	 * The ANSI color red.
	 */
	public static final String red = esc + "[0;31m";
	
	/**
	 * The ANSI color green.
	 */
	public static final String green = esc + "[0;32m";
	
	/**
	 * The ANSI color yellow.
	 */
	public static final String yellow = esc + "[0;33m";
	
	/**
	 * The ANSI color blue.
	 */
	public static final String blue = esc + "[0;34m";
	
	/**
	 * The ANSI color magenta.
	 */
	public static final String magenta = esc + "[0;35m";
	
	/**
	 * The ANSI color cyan.
	 */
	public static final String cyan = esc + "[0;36m";

	/**
	 * The ANSI color grey.
	 */
	public static final String grey = esc + "[0;37m";


	/**
	 * The ANSI color light black.
	 */
	public static final String li_black = esc + "[1;30m";

	/**
	 * The ANSI color light red.
	 */
	public static final String li_red = esc + "[1;31m";

	/**
	 * The ANSI color light green.
	 */
	public static final String li_green = esc + "[1;32m";

	/**
	 * The ANSI color light yellow.
	 */
	public static final String li_yellow = esc + "[1;33m";

	/**
	 * The ANSI color light blue.
	 */
	public static final String li_blue = esc + "[1;34m";

	/**
	 * The ANSI color light magenta.
	 */
	public static final String li_magenta = esc + "[1;35m";

	/**
	 * The ANSI color light cyan.
	 */
	public static final String li_cyan = esc + "[1;36m";

	/**
	 * The ANSI color light grey.
	 */
	public static final String li_grey = esc + "[1;37m";



	/**
	 * The ANSI highlight character.
	 */
	public static final String highlight = esc + "[1m";
	
	/**
	 * The ANSI blink character.
	 */
	public static final String blink = esc + "[5m";
	
	/**
	 * The ANSI inverse character.
	 */
	public static final String inverse = esc + "[7m";
	
	/**
	 * The ANSI reset character.
	 */
	public static final String normal = esc + "[0m";
	
	/**
	 * An array of all the colors in this.
	 */
	public static final String colors[] = {black, red, green, yellow, blue, magenta, cyan, grey, li_black, li_red, li_green, li_yellow, li_blue, li_magenta, li_cyan, li_grey};

	/**
	 * The ANSI character for clearing the terminal screen.
	 */
	public static final String clrscr = esc + "[2J" + esc + "[0;0H";

	
	/**
	 * The ANSI clear line character.
	 */
	public static final String clrline = esc + "[2K\r";


	
	/**
	 * The ANSI creturn character.
	 */
	public static final char creturn = ((char) 13);
	
	/**
	 * The ANSI newline character.
	 */
	public static final char newline = ((char) 10);
	
	/**
	 * The ANSI crlf character.
	 */
	public static final String crlf = "" + newline + creturn;
	/**
	 * The character that deletes the previous one.
	 */
	public static final char delchar = ((char) 127);
	/**
	 * The character that deletes the previous one, except for Windows.
	 */
	public static final char delchar2 = ((char) 8);	// This is for MS Telnet

	/**
	 * The up arrow on your keyboard.
	 */
	public static final char key_up[] = {(char) 27, (char) 91, (char) 65};
	/**
	 * The left arrow on your keyboard.
	 */
	public static final char key_left[] = {(char) 27, (char) 91, (char) 68};
	/**
	 * The right arrow on your keyboard.
	 */
	public static final char key_right[] = {(char) 27, (char) 91, (char) 67};
	/**
	 * The down arrow on your keyboard.
	 */
	public static final char key_down[] = {(char) 27, (char) 91, (char) 66};


	/**
	 * Returns the character that will take you to coordinates in the terminal window.
	 * @param row The row that you want
	 * @param col The column that you want
	 * @return The ANSI string that takes you to those coordinates
	 */
	public static String gotoxy(int row, int col) {
		return esc + "[" + row + ";" + col + "H";
	}

	// Strip off VT100 commands and line discipline (for cleantext output or whatever)
	/**
	 * Removes all the formatting
	 * @param line The input to remove from
	 * @return The "clean" string
	 */
	public static String strip_all(String line) {
		int i;
		StringBuffer nline = new StringBuffer();
	
		for(i = 0; i < line.length(); i++) {
			while(i < line.length() && line.charAt(i) == esc.charAt(0)) {	// Got escape sequence
				// OK. All VT100 cmds seems to end either with m or H
				while(i < line.length() && line.charAt(i) != 'm' && line.charAt(i) != 'H')
					i++;
				i++;	// Skip m or H
			}
			if(i >= line.length()) {	// Did we hit the end?
				break;
			}
			
			// At last, check for special keys
			if(line.charAt(i) != creturn && line.charAt(i) != newline && line.charAt(i) != delchar) {
				nline.append(line.charAt(i));
			}
		}
	
		return nline.toString();
	}

	/**
	 * Strips all the VT100 characters off
	 * @param line The input string
	 * @return The "clean" string
	 */
	public static String strip_vt100(String line) {
		int i;
		StringBuffer nline = new StringBuffer();
	
		for(i = 0; i < line.length(); i++) {
			while(i < line.length() && line.charAt(i) == esc.charAt(0)) {	// Got escape sequence
				// OK. All VT100 cmds seems to end either with m or H
				while(i < line.length() && line.charAt(i) != 'm' && line.charAt(i) != 'H')
				i++;
				i++;	// Skip m or H
			}
				if(i >= line.length()) {	// Did we hit the end?
					break;
				}
				nline.append(line.charAt(i));
		}
		return nline.toString();
	}
}
