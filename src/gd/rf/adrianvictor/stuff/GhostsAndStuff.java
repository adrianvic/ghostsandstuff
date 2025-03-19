package gd.rf.adrianvictor.stuff;

import gd.rf.adrianvictor.lib.ConfigurationEx;
import gd.rf.adrianvictor.lib.Log;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;

public class GhostsAndStuff extends JavaPlugin {
    ConfigurationEx config;
    PluginManager pm;
    Log logger;

    @Override
    public void onDisable() {
        logger.info("is being disabled.");
    }

    @Override
    public void onEnable() {
        logger = new Log(this);
        pm = this.getServer().getPluginManager();
        config = new ConfigurationEx(this, "config.yml", logger);
        config.loadConfig();
        loadModules();
        logger.info("is starting.");
    }
    public void loadModules() {
    	
    	// AntiSpam
    	if (this.getConfiguration().getBoolean("antiSpam", true)) {
    	PlayerListener antiSpam = new AntiSpam(this);
        pm.registerEvent(Type.PLAYER_CHAT, antiSpam, Priority.High, this);
    	}
        
    	// RainbowChat
        if (this.getConfiguration().getBoolean("rainbowChat", true)) {
    	PlayerListener rainbowChat = new RainbowChat(this);
        logger.info("Loading module RainbowChat");
        pm.registerEvent(Type.PLAYER_CHAT, rainbowChat, Priority.High, this);
        }

        // SkibidiBlocker
        if (this.getConfiguration().getBoolean("skibidiBlocker", true)) {
            logger.info("Loading module SkibidiBlocker");
            List<String> words = this.getConfiguration().getStringList("skibidiBlockerWords", null);
            if (words == null || words.isEmpty()) {
                logger.warning("SkibidiBlocker is enabled, but no words were provided. Disabling.");
            } else {
                PlayerListener verboseWorldChange = new SkibidiBlocker(this, logger);
                pm.registerEvent(Type.PLAYER_CHAT, verboseWorldChange, Priority.High, this);
            }
        }
    }
}
