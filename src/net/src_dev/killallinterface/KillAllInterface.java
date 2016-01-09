package net.src_dev.killallinterface;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import net.src_dev.killallinterface.listeners.CommandPreprocessListener;

public final class KillAllInterface extends JavaPlugin{
	
	@Override
	public void onEnable(){
		saveDefaultConfig();
		Strings.loadStrings(getConfig());
		
		getServer().getPluginManager().registerEvents(new CommandPreprocessListener(this), this);
		
		for(String s:Strings.enableInfo) logInfo(s);
	}
	@Override
	public void onDisable(){
		reloadConfig();
		
		HandlerList.unregisterAll(this);
		
		for(String s:Strings.disableInfo) logInfo(s);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(label.equalsIgnoreCase("killallinterface")){
			if(args.length == 0){
				for(String s:Strings.info) sendMessage(sender, s.replace("%version%", Strings.version));
				return true;
			}
			if(args[0].equalsIgnoreCase("help")){
				if(!sender.hasPermission("killallinterface.help")){
					sendMessage(sender, Strings.noPermission);
					return true;
				}
				for(String s:Strings.help) sendMessage(sender, s);
				return true;
			}
			if(args[0].equalsIgnoreCase("reload")){
				if(!sender.hasPermission("killallinterface.reload")){
					sendMessage(sender, Strings.noPermission);
					return true;
				}
				reload();
				sendMessage(sender, Strings.reloaded);
			}
			sendMessage(sender, Strings.commandNonExistant);
			return true;
		}
		return false;
	}
	public void reload(){
		onDisable();
		onEnable();
	}
	
	public void sendMessage(CommandSender sender, String msg){
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
	public void sendMessage(Player player, String msg){
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
	public void logInfo(String info){
		getLogger().info(info);
	}
}
