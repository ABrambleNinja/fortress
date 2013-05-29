/*
Fortress Telnet Server
Copyright (c) ABrambleNinja, fortress.syntaxes.org

Released under the MIT license <http://abrambleninja.mit-license.org>
*/
package org.syntaxes.fortress.plugin;
import org.syntaxes.fortress.command.*;
import org.syntaxes.fortress.*;
/**
 * The base interface of a plugin. Implement this to make a plugin.
 * @author ABrambleNinja
 *
 */
public interface Plugin {
	
	/**
	 * What to do when your plugin is enabled
	 */
	public void onEnable();
	
	/**
	 * onCommand.      See the <a href="http://wiki.fortress.syntaxes.org" target="_blank">plugin tutorial</a> to use this.
	 * @param sender   The user who sent the message
	 * @param command  The command used. Contains arguments also.
	 * @return         Tells the server whether or not it worked.
	 */
	public boolean onCommand(User sender, Command command);
	
	/**
	 * What to do when your plugin is disabled.
	 */
	public void onDisable();
}
