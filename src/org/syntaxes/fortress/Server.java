/*
Fortress Telnet Server
Copyright (c) ABrambleNinja, fortress.syntaxes.org

Released under the MIT license <http://abrambleninja.mit-license.org>
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

