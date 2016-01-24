package net.src_dev.killallinterface.listeners;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import net.src_dev.killallinterface.KillAllInterface;
import net.src_dev.killallinterface.Messages;

public class CommandPreprocessListener implements Listener{
	private KillAllInterface plugin;
	public CommandPreprocessListener(KillAllInterface plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommandPreprocessEvent(PlayerCommandPreprocessEvent e){
		String[] args = e.getMessage().split(" ");
		if((args[0].equalsIgnoreCase("/killall") && plugin.getConfig().getBoolean("interface.killall")) || (args[0].equalsIgnoreCase("/mobkill") && plugin.getConfig().getBoolean("interface.mobkill"))){
			if(args[1].equalsIgnoreCase("all")){
				List<String> ignoreList = plugin.getConfig().getStringList("all-ignore");
				for(String s:ignoreList) ignoreList.set(ignoreList.indexOf(s), s.toLowerCase());
				for(String s:KillAllInterface.entityTypes){
					if(!ignoreList.contains(s)){
						if(args.length > 2){
							plugin.getServer().dispatchCommand(e.getPlayer(), args[0].substring(1) + " " + s + " " + args[2]);
						}
						else{
							plugin.getServer().dispatchCommand(e.getPlayer(), args[0].substring(1) + " " + s);
						}
					}
				}
				e.setCancelled(true);
				return;
			}
			boolean contains = false;
			for(String s:plugin.getConfig().getStringList("disallow")){
				if(args[1].equalsIgnoreCase(s)){
					contains = true;
					break;
				}
			}
			if(contains){
				Messages.entityNotAllowed.send(e.getPlayer());
				e.setCancelled(true);
				return;
			}
		}
	}
}
