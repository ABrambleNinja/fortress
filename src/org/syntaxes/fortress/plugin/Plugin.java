package org.syntaxes.fortress.plugin;
import org.syntaxes.fortress.command.*;
import org.syntaxes.fortress.*;
/**
 * The base interface of a plugin. Implement this to make a plugin.
 * @author ABrambleNinja
 *
 */
public interface Plugin {
	public boolean onCommand(User sender, Command command, String[] args);
}
