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

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
 * Main class for the Telnet server.
 * @author ABrambleNinja
 *
 */
public class Server {
	/**
	 * Starts the server on port 23 or specified.
	 * @param args Run with the optional integer port and it will run on that.
	 */
	public static void main(String[] args) {
		//TODO: See TODO.md on Github (https://github.com/ABrambleNinja/fortress/blob/master/TODO.md)
		int port = 23; //default port for starting server
		if(args.length>0) {
			try {
				port = Integer.parseInt(args[0]);
			}
			catch (NumberFormatException e) {
				System.out.println("Error: Not a number. Assuming 23.");
				port = 23;
			}
		}
		else {
			port = 23;
		}
		try {
			Logo.printLogo();
			ServerSocket listener = new ServerSocket(port); //start the server to listen on port "port"
			System.out.println(Colors.normal+"\n\nServer started on port "+port+" on "+Time.now("EEEE, MMMM d, yyyy 'at' h:mm a.")+"\n\n");  //echo startup time to console
			System.out.println(Colors.normal+"\n"); //reset colors
			(new Console()).start();

			while(true) {
				Socket client = listener.accept(); //poll for new users
				System.out.println(Colors.normal+"New client  from " + client.getInetAddress().getHostName() + " on client's port " + client.getPort());
				(new UserHandler (client)).start();
			}			
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
}
/***
 * Class that makes a thread for each user, gets their chat messages, and sends them off to ChatHandler.
 * @author ABrambleNinja
 *
 */
class UserHandler extends Thread {
	Socket clientSock;
	UserHandler(Socket s) {
		this.clientSock = s;		
	}
	public void run() {
		Logo.printLogo(clientSock);
		try {
			ChatHandler.sendRaw(Colors.normal+"Please enter the desired alias you wish to have on this chat server: ", clientSock);
			Scanner readFromClient = new Scanner(clientSock.getInputStream());
			String name="";
			try {
				name = readFromClient.nextLine().trim();
			}
			catch (java.util.NoSuchElementException e) {}
			User user = new User();
			ChatHandler.addUser(clientSock, user, name);
			while(true) {
				String message = "";
				try {
					message= readFromClient.nextLine().trim();
				}
				catch (java.util.NoSuchElementException e) {
					ChatHandler.removeUser(user);
					break;
				}
				ChatHandler.chat(message, user);
			}
			ChatHandler.removeUser(user);
			clientSock.close();
		} catch (IOException e) {}
	} 
	
}

class User {
		private String name;
		private Socket userSocket;
		private boolean printTime = false;
		private boolean isAdmin = false;
		
		public String getName() { //getter for name
			return name;
		}
		public Socket getSocket() { //getter for socket
			return userSocket;
		}
		public boolean setName(String newName) { //setter for name
			if(newName!=null) {
				name=newName;
				return true;
			}
			else {
				return false;
			}
		}
		public boolean setSocket(Socket newSocket) { //setter for socket
			userSocket = newSocket;
			return true;
		}
		public boolean getPrintTime() {
			return printTime;
		}
}
/*
 * Adds a new class called Console for the command line interface
 * 
 * In the process of implementing
 * 
 */
class Console extends Thread { 
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