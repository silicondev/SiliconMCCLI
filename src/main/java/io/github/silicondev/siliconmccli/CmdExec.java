package io.github.silicondev.siliconmccli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdExec implements CommandExecutor {
	private List<Cmd> commands;
	private List<Run> codes;
	
	public CmdExec(List<Cmd> commands, List<Run> codes) {
		this.commands = commands;
		this.codes = codes;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Cmd[] getCom = new Cmd[commands.size()];
		commands.toArray(getCom);
		
		for (int i = 0; i < getCom.length; i++) {     //Tests first word for command initialiser, then the rest as multi args.
			String currentCommand = getCom[i].inputName;
			
			if (cmd.getName().equalsIgnoreCase(currentCommand)) {     //Correct command found.
				List<String> finArgs = new ArrayList<String>(Arrays.asList(args));
				
				Cmd runCmd = getCom[i];
				
				if (getCom[i].canChildren && getCom[i].children.size() != 0) {
					boolean found = false;
					int argsFrom = 0;
					
					List<Cmd> children = getCom[i].children;
					for (int a = 0; a < finArgs.size() && !found; a++) {
						
						boolean childFound = false;
						for (int c = 0; c < children.size() && !childFound; c++) {
							if (children.get(c).inputName.equalsIgnoreCase(finArgs.get(a))) {
								childFound = true;
								if (children.get(c).canChildren && children.get(c).children.size() != 0) {
									children = children.get(c).children;
								} else {
									found = true;
									runCmd = children.get(c);
									argsFrom = a + 1;
								}
							}
						}
					}
					
					if (argsFrom != 0) {
						for (int d = 0; d < argsFrom; d++) {
							finArgs.remove(0);
						}
					}
				}
				
				
				
				if (runCmd.playerOnly) {
					if (sender instanceof Player) {
						Player player = (Player) sender;
						if (player.hasPermission(runCmd.permNode) || runCmd.permNode == "default") {
							commandHandle(runCmd.outputID, finArgs, sender);
						} else {
							sender.sendMessage(Lang.TITLE.toString() + Lang.ERR_NOPERM_1.toString() + runCmd.permNode + Lang.ERR_NOPERM_2.toString());
						}
					} else {
						sender.sendMessage(Lang.TITLE.toString() + Lang.ERR_PLAYERONLY.toString());
					}
				} else {
					if (sender instanceof Player) {
						Player player = (Player) sender;
						if (player.hasPermission(runCmd.permNode) || runCmd.permNode == "default") {
							commandHandle(runCmd.outputID, finArgs, sender);
						} else {
							sender.sendMessage(Lang.TITLE.toString() + Lang.ERR_NOPERM_1.toString() + runCmd.permNode + Lang.ERR_NOPERM_2.toString());
						}
					}
				}
				return true;
			}
		}
		return false;
	}
	
	public void commandHandle(int id, List<String> args, CommandSender sender) {
		boolean hasRun = false;
		
		if (args.size() < commands.get(id).reqParams) {     //Checks if command has correct amount of arguments, between the required and max. (Can allow for optional arguments)
			sender.sendMessage(Lang.TITLE.toString() + Lang.ERR_NEGARG.toString() + Integer.toString(args.size()) + "/" + Integer.toString(commands.get(id).reqParams));
		} else if ((!commands.get(id).noMaxParams) && args.size() > commands.get(id).maxParams) {
			sender.sendMessage(Lang.TITLE.toString() + Lang.ERR_POSARG.toString() + Integer.toString(args.size()) + "/" + Integer.toString(commands.get(id).maxParams));
		} else {
			for (int i = 0; i < codes.size(); i++) {
				if (id == codes.get(i).getId()) {
					codes.get(i).runCode();
					hasRun = true;
					break;
				}
			}
		}
		
		if (!hasRun) {
			sender.sendMessage(Lang.TITLE.toString() + Lang.ERR_NOCOMMAND.toString() + " (" + Integer.toString(id) + ")");
		}
	}
}