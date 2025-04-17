package gd.rf.adrianvictor.stuff;

import java.util.List;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

import gd.rf.adrianvictor.lib.Color;
import gd.rf.adrianvictor.lib.Log;

public class CommandTypoBlocker extends PlayerListener {
    JavaPlugin plugin;
    Log logger;

    public CommandTypoBlocker(JavaPlugin _plugin, Log _logger) {
        plugin = _plugin;
        logger = _logger;
    }

    public void onPlayerChat(PlayerChatEvent event) {
        String message = event.getMessage();
        List<String> protectedCommands = plugin.getConfiguration().getStringList("commandTypoBlockerWords", null);
        
        if (protectedCommands == null) {
            logger.warning("Commands list is null!");
            return;
        }
        if (protectedCommands.isEmpty()) {
            logger.warning("Commands list is empty!");
            return;
        }

        boolean caseSensitive = plugin.getConfiguration().getBoolean("commandTypoBlockerCaseSensitive", true);
        for (String protectedWord : protectedCommands) {
        	String finalMessage;
            if (!caseSensitive) {
            	finalMessage = message.toLowerCase();
            } else {            
            	finalMessage = message;
            }
            if (message.startsWith(protectedWord.toLowerCase()) || message.startsWith(protectedWord.toLowerCase(), 1)) {
            	if (plugin.getConfiguration().getBoolean("commandTypoBlockerVerbose", true)) {
            		event.getPlayer().sendMessage(Color.formatColors(plugin.getConfiguration().getString("commandTypoBlockerVerboseMessage", "&cYour message begins with a sensitive command, you may be leaking something sensitive by forgetting the slash!")));
            	}
            	event.setCancelled(true);
            }
        }
    }
}
