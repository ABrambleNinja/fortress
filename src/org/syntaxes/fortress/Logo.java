/*
Fortress Telnet Server
Copyright (c) ABrambleNinja, fortress.syntaxes.org

Released under the MIT license <http://abrambleninja.mit-license.org>
*/
package org.syntaxes.fortress;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * Contains the logo and logo-related functions.
 * @author ABrambleNinja
 *
 */
public class Logo {
	/**
	 * Contains the version string.
	 */
	public static final String version = "0.0.1";
	
	
	private static final String logo[] = {
		Colors.green+"w e l c o m e . t o . t h e . n e t . w h e r e . e v e r y t h i n g . h a p p",
		Colors.green+"e n s . w e l c o m e . t o . t h e . n e t . w h e r e . t h e r e . a r e . m",
		Colors.green+"o n s t e r s . w e l c o m e . t o . t h e . n e t . w h e r e . e v e r y t h",
		Colors.green+"i n g . i s . a . m o n s t e r . w e l c o m e . t o . t h e . n e t . w h e r",
		Colors.green+"e . t h e r e . i s . e v i l . w e l c o m e . t o . t h e . n e t . w h e r e",
		Colors.green+"e v e r y t h i n g . i s . e v i l . w e l c o m e . t o . t h e . n e t . y o",
		Colors.green+"u . c a n n o t                                                   . e s c a p e",
		Colors.green+"f r o m . w e l      < < [ s t a t u s : f a l l i n g ] > >      c o m e . t o",
		Colors.green+"t h e . n e t .                                                   w h e r e . t",
		Colors.green+"h e r e . a r e . s h a d o w s . w e l c o m e . t o . t h e . n e t . w h e r",
		Colors.green+"e . e v e r y t h i n g . i s . a . s h a d o w . w e l c o m e t o . t h e . n",
		Colors.green+"e t . w h e r e . a n y t h i n g . c a n . h a p p e n . w e l c o m e . t o .",
		Colors.green+"t h e . n e t . w h e r e . e v e r y t h i n g . w i l l . h a p p e n . w e l",
		Colors.green+"c o m e . t o . t h e . n e t . w h i c h . i s . y o u r . d o o m . w e l c o",
		Colors.green+"m e . t o . t h e . n e t . t h a t . i s . o m n i s c i e n t . w e l c o m e"
    };

	/** 
	 * Prints out the logo to the console.
	 */
	public static void printLogo() {
		for(String eachline:logo) { //grab logo from logo.java and output it line by line
			System.out.println(eachline);
		}
		System.out.print(Colors.normal);
	}
	/**
	 * Prints out the logo to a socket
	 * @param toPrint the socket to print it to
	 */
	public static void printLogo(Socket toPrint) {
		try {
			PrintWriter writeToClient = new PrintWriter(toPrint.getOutputStream(), true);
			for(String eachline:logo) { //grab logo from logo.java and output it line by line
				writeToClient.println(eachline);
			}
			writeToClient.print(Colors.normal);
		}
		catch(IOException e) {}
	}
}

