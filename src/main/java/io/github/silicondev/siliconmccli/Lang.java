package io.github.silicondev.siliconmccli;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public enum Lang {
	TITLE("plugin-name", "&f[&eSiliconMCCLI&f] &r"),
	TEST_ARG("test-arg", "Test Command successful! Test arg: "),
	TEST_NOARG("test-noarg", "Test command successful with no arguments!"),
	/*ERRORS*/
	ERR_NOPERM_1("err-noperm-1", "ERR: You need the perm node "),
	ERR_NOPERM_2("err-noperm-2", " to run that command! Contact an admin if you think this is a mistake."),
	ERR_PLAYERONLY("err-playeronly", "ERR: Command can only be run as a player!"),
	ERR_NEGARG("err-negarg", "ERR: Not enough arguments: "),
	ERR_POSARG("err-posarg", "ERR: Too many arguments: "),
	ERR_INVARG("err-invarg", "ERR: Invalid argument!"),
	ERR_NOCOMMAND("err-nocommand", "ERR: Unknown command!");
	
	
	private String path;
	private String def;
	private static FileConfiguration LANG;
	
	Lang(String path, String def) {
		this.path = path;
		this.def = def;
	}
	
	public static void setFile(FileConfiguration langConfig) {
		LANG = langConfig;
	}
	
	@Override
	public String toString() {
		if (LANG.getString(this.path, def).contains("&")) {
			return String.valueOf(ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def)));
		} else {
			return LANG.getString(this.path, def);
		}
	}
	
	public String getDefault() {
		return this.def;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public String getString() {
		return LANG.getString(this.path, def);
	}
	
	public String getDefaultString() {
		return this.def;
	}
}
