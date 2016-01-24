/**
 * Copyright 2016 src-dev.net
 * All rights reserved.
 */
package net.src_dev.killallinterface;

import org.bukkit.configuration.file.FileConfiguration;

import net.src_dev.killallinterface.library.messageapi.Message;
import net.src_dev.killallinterface.library.messageapi.MessageHandler;
import net.src_dev.killallinterface.library.messageapi.MultiMessage;

public class Messages extends MessageHandler {
	public Messages(FileConfiguration config) {
		super(config);
		options().setKeyPrefix("strings.");
		load();
	}
	
	public static MultiMessage enabled = new MultiMessage(new String[]{
			"[KillAllInterface] Version " + KillAllInterface.version,
			"[KillAllInterface] By S_Ryan",
			"[KillAllInterface] Successfully enabled."
			});
	public static Message disabled = new Message("[KillAllInterface] Disabled.");
	
	public static Message reloaded;
	public static MultiMessage info;
	public static MultiMessage help;
	public static Message noPermission;
	public static Message commandNonExistant;
	public static Message entityNotAllowed;
	
	public void load() {
		reloaded = getConfigMessage("reloaded").color();
		info = getConfigMultiMessage("info").color().replace("%version%", KillAllInterface.version);
		help = getConfigMultiMessage("help").color();
		noPermission = getConfigMessage("noPermission").color();
		commandNonExistant = getConfigMessage("commandNonExistant").color();
		entityNotAllowed = getConfigMessage("entityNotAllowed").color();
	}
}
