package io.github.silicondev.siliconmccli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.command.CommandSender;

public class Handler {
	public boolean Handle(String command, CommandSender sender, List<CLICommand> listedCommands, char commandSplit) {
		List<String> splittedCommands = Arrays.asList(command.split(String.valueOf(commandSplit)));
		
		if (splittedCommands.size() > 0 && listedCommands.size() > 0) {
			List<String> args = new ArrayList<String>();
			args.addAll(splittedCommands);
			
			for (CLICommand cmd : listedCommands) {
				if (cmd.Input.equalsIgnoreCase(splittedCommands.get(0))) {
					Result res = cmd.Run(sender, args);
					if (res.Valid)
						return res.Success;
					
				}
			}
		}
		
		return false;
	}
}