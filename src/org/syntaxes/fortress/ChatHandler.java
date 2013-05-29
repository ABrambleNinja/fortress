/*
Fortress Telnet Server
Copyright (c) ABrambleNinja, fortress.syntaxes.org

Released under the MIT license <http://abrambleninja.mit-license.org>
*/
package org.syntaxes.fortress;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
/**
 * Handles all of the chat stuff.
 * If there's a command, it sends it off to CommandHandler.class
 * @author ABrambleNinja
 *
 */
public class ChatHandler {
	/**
	 * Contains a list of all the "User"s connected.
	 */
	public static List<User> userList = new ArrayList<User>(); //list of all connected users
	
	/**
	 * Does nothing. Not sure if necessary, but scared to remove it.
	 */
	public void run() {
		
	}
	/**
	 * Sends a message to everyone on the chat server, except console.
	 * Will eventually have a boolean called logToConsole, and setting
	 * it to "true" will have it write it to console, obviously.
	 * @param toSend The message to send.
	 */
	public static void sendRaw(String toSend) { //send to all
		try {
			for(User user:userList) {
				Socket userSocket = user.getSocket();
				PrintWriter p = new PrintWriter(userSocket.getOutputStream(),true);
				p.println(toSend);
			}
		}
		catch (IOException e) {}
		//System.out.println(toSend);
	}
	/**
	 * Sends a message to a certain user on the chat room.
	 * @param toSend The message to send.
	 * @param sendTo The user to send it to.
	 */
	public static void sendRaw(String toSend, User sendTo) { //send to specified user
		try {
			Socket userSocket = sendTo.getSocket();
			PrintWriter p = new PrintWriter(userSocket.getOutputStream(), true);
			p.println(toSend);
		}
		catch (IOException e) {}
		//System.out.println(toSend + " was sent to "+sendTo.getName());
	}
	/**
	 * Sends a message to a certain socket on the chat room.
	 * Used for when a user first connects and is not a User yet.
	 * @param toSend The message to send.
	 * @param sendTo The socket to send it to.
	 */
	public static void sendRaw(String toSend, Socket sendTo) {
		try {
			PrintWriter p = new PrintWriter(sendTo.getOutputStream(), true);
			p.println(toSend);
		}
		catch (IOException e) {}
		//System.out.println(toSend + " was sent to a socket with IP "+sendTo.getInetAddress().getHostAddress());
	}
	/**
	 * Adds a user to the User list and sends a message to everyone on as well as console.
	 * @param userSocket The socket the user is on.
	 * @param user An arbirtrary User variable that gets added.
	 * @param nickname The nickname the user requested.
	 */
	public synchronized static void addUser (Socket userSocket, User user, String nickname) { //adds user and broadcasts new user
		userList.add(user);
		if(user.setName(nickname)) {
			user.setName(nickname);
		}
		user.setSocket(userSocket);
		sendRaw(Colors.red+user.getName()+Colors.normal+" has joined the chatroom.");
		System.out.println(Colors.red+user.getName()+Colors.normal+" has joined the chatroom.");
	}
	/**
	 * Removes a user from the list and announces that they disconnected.
	 * @param user The user to remove.
	 */
	public synchronized static void removeUser (User user) { //removes user and broadcasts removal
		userList.remove(user);
		sendRaw(Colors.red+user.getName()+Colors.normal+" has left the chatroom.");
	}
	/**
	 * Handles chat, and if the chat message starts with a '.', it sends it to CommandHandler.class
	 * @param input The message the user typed in
	 * @param sender The user that sent it. Eventually will be Object that is an instanceof either User or Console.
	 */
	public static void chat(String input, User sender) {
		
		try {
			if(input.charAt(0)=='.') { //if it starts with . it is a command, handle it as such
				input = input.substring(1); //cut off the .
					CommandHandler.command(input, sender.getName()); //handle it using the command() subroutine
			}
			else {
				System.out.println(sender.getName()+" ("+Time.now("EEEE, MMMM d, yyyy 'at' h:mm a")+"): "+input); //log to console with time
				for (User user :  userList) {
					boolean timeSetting = user.getPrintTime();
		    	   	if(timeSetting) {
		    	   		sendRaw(Colors.red + sender.getName() + Colors.blue + " at "+ Time.now()+Colors.normal +": " + input + "\n"+Colors.normal, user);	
		    	  	}
		    	   	else {
		    	   		sendRaw(Colors.red + sender.getName() + Colors.normal+": " + input + "\n"+Colors.normal, user);	
		    	   	}
				}
			}
		}
		catch (java.lang.StringIndexOutOfBoundsException e) {}
	}
}
