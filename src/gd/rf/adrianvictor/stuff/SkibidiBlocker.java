package gd.rf.adrianvictor.stuff;

import java.util.List;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

import gd.rf.adrianvictor.lib.Color;
import gd.rf.adrianvictor.lib.Log;

public class SkibidiBlocker extends PlayerListener {
    JavaPlugin plugin;
    Log logger;

    public SkibidiBlocker(JavaPlugin _plugin, Log _logger) {
        plugin = _plugin;
        logger = _logger;
    }

    public void onPlayerChat(PlayerChatEvent event) {
        String message = event.getMessage();
        List<String> blockedWords = plugin.getConfiguration().getStringList("skibidiBlockerWords", null);
        
        if (blockedWords == null) {
            logger.warning("Blocked words list is null!");
            return;
        }
        if (blockedWords.isEmpty()) {
            logger.warning("Blocked words list is empty!");
            return;
        }

        boolean caseSensitive = plugin.getConfiguration().getBoolean("skibidiBlockerCaseSensitive", true);
        for (String blockedWord : blockedWords) {
        	String finalMessage;
            if (!caseSensitive) {
            	finalMessage = message.toLowerCase();
            } else {            
            	finalMessage = message;
            }
            if (message.contains(blockedWord.toLowerCase())) {
            	if (plugin.getConfiguration().getBoolean("skibidiBlockerVerbose", true)) {
            		event.getPlayer().sendMessage(Color.formatColors(plugin.getConfiguration().getString("skibidiBlockerVerboseMessage", "This is a bad word.")));
            	}
            	event.getPlayer().getWorld().strikeLightning(event.getPlayer().getLocation());
            }
        }
    }
}
