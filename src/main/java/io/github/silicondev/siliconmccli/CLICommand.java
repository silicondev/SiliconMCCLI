package io.github.silicondev.siliconmccli;

import java.util.List;
import java.util.ArrayList;
import org.bukkit.command.CommandSender;

public class CLICommand {
	
	public String Input; // This will be the string the player types for the command.
	public String Id; // This is an internal identifier for commands with the same Input but different locations on the hierarchy.
	public List<CLICommand> Children; // This contains the children commands of the hierarchy.
	public ICLIOutput Output; // The output class for runnable code.
	
	public CLICommand(String _input, String _id, ICLIOutput _output) {
		Children = new ArrayList<CLICommand>();
		setup(_input, _id, _output);
	}
	
	public CLICommand(String _input, String _id, ICLIOutput _output, List<CLICommand> _children) {
		Children = _children;
		setup(_input, _id, _output);
	}
	
	private void setup(String _input, String _id, ICLIOutput _output) {
		Input = _input;
		Id = _id;
		Output = _output;
	}
	
	public Result Run(CommandSender sender, List<String> args) {
		
		List<String> useArgs = new ArrayList<String>();
		useArgs.addAll(args);
		useArgs.remove(0);
		
		if (!Children.isEmpty() && !useArgs.isEmpty()) {
			for (CLICommand child : Children) {
				if (child.Input.equalsIgnoreCase(useArgs.get(0))) {
					return child.Run(sender, useArgs);
				}
			}
			return new Result(false, false);
		} else {
			return new Result(true, Output.Run(sender, useArgs));
		}
	}
}
