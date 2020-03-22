package io.github.silicondev.siliconmccli;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Handler implements CommandExecutor {
	
	private List<CLICommand> _commands;
	private boolean _debug = false;
	
	public Handler(List<CLICommand> commands) {
		_commands = commands;
	}
	
	public Handler(List<CLICommand> commands, boolean debug) {
		_commands = commands;
		_debug = debug;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (_debug) {
			
			String argString = "";
			for (String str : args) {
				argString += str + " ";
			}
			
			sender.sendMessage("[SiliconMCCLI] Received command input: " + command.getName() + " " + argString.trim());
		}
		
		for (CLICommand cmd : _commands) {
			if (cmd.Input.equalsIgnoreCase(command.getName())) {
				Result res = cmd.Run(sender, Arrays.asList(args));
				if (res.Valid)
					return res.Success;
			}
		}
		
		return false;
	}
}