package io.github.silicondev.siliconmccli;

import java.util.List;
import org.bukkit.command.CommandSender;

public interface ICLIOutput {
	Result Run(CommandSender sender, List<String> args);
}
