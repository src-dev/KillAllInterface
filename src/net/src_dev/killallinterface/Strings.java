package net.src_dev.killallinterface;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class Strings {	
	public final static String version = "1.0.2";
	public final static String[] enableInfo = {
		"[KillAllInterface] Version " + Strings.version,
		"[KillAllInterface] By S_Ryan",
		"[KillAllInterface] Successfully enabled."
	};
	public final static String[] disableInfo = {
		"[KillAllInterface] Disabled."
	};
	public final static String[] entityTypes = {
		"tamed",
		"named",
		"drops",
		"arrows",
		"boats",
		"minecarts",
		"xp",
		"paintings",
		"itemframes",
		"endercrystals",
		"monsters",
		"animals",
		"ambient"
	};
	
	public static String reloaded;
	public static List<String> info;
	public static List<String> help;
	public static String noPermission;
	public static String commandNonExistant;
	public static String entityNotAllowed;
	public static void loadStrings(FileConfiguration config){
		info = config.getStringList("strings.info");
		help = config.getStringList("strings.help");
		reloaded = config.getString("strings.reloaded");
		noPermission = config.getString("strings.noPermission");
		commandNonExistant = config.getString("strings.commandNonExistant");
		entityNotAllowed = config.getString("strings.entityNotAllowed");
	}
}
