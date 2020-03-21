package io.github.silicondev.siliconmccli;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Handler implements CommandExecutor {
	
	private List<CLICommand> _commands;
	
	public Handler(List<CLICommand> listedCommands) {
		_commands = listedCommands;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
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