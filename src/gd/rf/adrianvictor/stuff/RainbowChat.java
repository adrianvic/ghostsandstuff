package gd.rf.adrianvictor.stuff;

import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

import gd.rf.adrianvictor.lib.Color;

public class RainbowChat extends PlayerListener {
	JavaPlugin plugin;
	
	public RainbowChat(JavaPlugin _plugin) {
		plugin = _plugin;
	}
	
	public void onPlayerChat(PlayerChatEvent event) {
		StringBuilder result = new StringBuilder();
		String message = Color.formatColors(event.getMessage());
        int index = 0;
        
        while (index < message.length()) {
            int startIndex = message.indexOf("§z", index);
            if (startIndex == -1) {
                result.append(message.substring(index));
                break;
            }
            
            result.append(message, index, startIndex);
            int endIndex = startIndex + 2;
            
            while (endIndex < message.length() && message.charAt(endIndex) != '§') {
                endIndex++;
            }
            
            String textToColor = message.substring(startIndex + 2, endIndex);
            result.append(Color.rainbow(textToColor));
            
            index = endIndex;
        }
        event.setMessage(result.toString());
}
}
