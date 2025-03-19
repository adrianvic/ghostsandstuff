package gd.rf.adrianvictor.stuff;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

import gd.rf.adrianvictor.lib.PlayerEx;

public class AntiSpam extends PlayerListener {
    private final Map<UUID, String> lastMessages = new HashMap<>();
    private JavaPlugin plugin;
    
    public AntiSpam(JavaPlugin plugin) {
    	this.plugin = plugin;
    }
    
	public void onPlayerChat(PlayerChatEvent event) {
		if (lastMessages.containsKey(event.getPlayer().getUniqueId()) && lastMessages.get(event.getPlayer().getUniqueId()).contains(event.getMessage())) {
			event.setCancelled(true);
			event.getPlayer().sendMessage("Stop spamming.");
			
			if (plugin.getConfiguration().getBoolean("antiSpamLightning", false)) PlayerEx.strikeLightning(event.getPlayer());
		}
		lastMessages.put(event.getPlayer().getUniqueId(), event.getMessage());
	}
}
