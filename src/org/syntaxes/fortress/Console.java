/*
Fortress Telnet Server
Copyright (c) ABrambleNinja, fortress.syntaxes.org

Released under the MIT license <http://abrambleninja.mit-license.org>
*/

package org.syntaxes.fortress;

import java.util.Scanner;

/*
* Adds a new class called Console for the command line interface
* 
* In the process of implementing
* 
*/
public class Console extends Thread { 
public void run() {
	Scanner in = new Scanner(System.in);
	String message = "";
	while(true) {
		message = in.nextLine();
		chat(message);
		
	}
}
public static void chat(String input) {
	try {
		if(input.charAt(0)=='.') { //if it starts with . it is a command, handle it as such
			input = input.substring(1); //cut off the .
			command(input);
		}
	}
	catch(java.lang.StringIndexOutOfBoundsException e) {}
}
public static void command (String command) {
	System.out.println("Command used: "+command);
	
}

}