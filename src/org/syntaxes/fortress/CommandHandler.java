/*
Fortress Telnet Server
Copyright (c) ABrambleNinja, fortress.syntaxes.org

Released under the MIT license <http://abrambleninja.mit-license.org>
*/
package org.syntaxes.fortress;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Handles all of the commands that the user can throw at it.
 * @author ABrambleNinja
 *
 */
public class CommandHandler {
	/**
	 * Logs to console that someone used a command (eventually will handle it as well)
	 * @param command command that was used
	 * @param sender String of who sent it
	 */
	public static void command(String command, String sender) {
		System.out.println(sender+" ("+Time.now("EEEE, MMMM d, yyyy 'at' h:mm a")+") tried command: "+command);
		String[] args = command.split(" ");
	}
	
	
	/*
	 * Start the commands!
	 */
	/**
	 * Sends a message to all users in the format: [Server] Mesage.
	 * @param args Array of arguments
	 * @param sender The sender of the message (only gets logged to console)
	 */
	public static void say(String[] args, User sender) {
		//TODO: Add time checker
		System.out.println(Colors.green+"[Server] (\""+sender.getName()+"\"): "+convertArgs(args)+Colors.normal);
		ChatHandler.sendRaw(Colors.green+"[Server]"+convertArgs(args)+Colors.normal);
	}
	/**
	 * Lists all connected users back to whoever requested (Work in progress).
	 * @param sender User who requested the list
	 */
	public static void list(User sender) {
		System.out.println(sender.getName()+" used the command \".list\"");
		try {
			List<User> userList = ChatHandler.userList;
			
			
			PrintWriter p = new PrintWriter(sender.getSocket().getOutputStream(), true);
			p.println("User list command coming soon!");
		}
		catch (Exception e) {}
	}
	/**
	 * Converts an array of arguments for a command to a string with spaces as a delimiter. Mainly used for commands like ".say".
	 * @param args The array to convert
	 * @return The string with spaces seperating args
	 */
	public static String convertArgs (String[] args) {
		String modifiers = args[0];
		for(int count = 1;count<args.length;count++) {
			modifiers += " "+args[count];
		}
		return modifiers;
	}
	

}
