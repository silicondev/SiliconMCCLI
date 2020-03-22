package io.github.silicondev.siliconmccli;

import java.util.List;
import org.bukkit.command.CommandSender;

public interface ICLIOutput {
	boolean Run(CommandSender sender);
	boolean Run(CommandSender sender, List<String> args);
}
