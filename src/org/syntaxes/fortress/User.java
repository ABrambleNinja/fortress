/*
Fortress Telnet Server
Copyright (c) ABrambleNinja, fortress.syntaxes.org

Released under the MIT license <http://abrambleninja.mit-license.org>
*/
package org.syntaxes.fortress;

import java.net.Socket;
import java.util.Scanner;

public class User {
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
