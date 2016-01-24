package net.src_dev.killallinterface;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import net.src_dev.killallinterface.listeners.CommandPreprocessListener;

public final class KillAllInterface extends JavaPlugin{
	public final static String version = "1.0.3";
	
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
	
	@Override
	public void onEnable(){
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();

		new Messages(getConfig());
		
		getServer().getPluginManager().registerEvents(new CommandPreprocessListener(this), this);
		
		Messages.enabled.logAsInfo();
	}
	@Override
	public void onDisable(){
		reloadConfig();
		
		HandlerList.unregisterAll(this);
		
		Messages.disabled.logAsInfo();
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(label.equalsIgnoreCase("killallinterface")){
			if(args.length == 0){
				Messages.info.send(sender);
				return true;
			}
			if(args[0].equalsIgnoreCase("help")){
				if(!sender.hasPermission("killallinterface.help")){
					Messages.noPermission.send(sender);
					return true;
				}
				Messages.help.send(sender);
				return true;
			}
			if(args[0].equalsIgnoreCase("reload")){
				if(!sender.hasPermission("killallinterface.reload")){
					Messages.noPermission.send(sender);
					return true;
				}
				reload();
				Messages.reloaded.send(sender);
			}
			Messages.commandNonExistant.send(sender);
			return true;
		}
		return false;
	}
	public void reload(){
		onDisable();
		onEnable();
	}
}
