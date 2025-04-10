package gd.rf.adrianvictor.stuff.command;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gd.rf.adrianvictor.lib.Color;
public class GetTime implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("gettime")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                long totalTicks = player.getWorld().getTime();
                int totalMinutes = (int) (totalTicks * 1440 / 24000);
                int hours = (totalMinutes / 60) + 6;
                int minutes = totalMinutes % 60;

                sender.sendMessage(Color.formatColors("The time for " + player.getWorld().getName() + " is &a" + hours + " hours &rand &a" + minutes + " minutes &r(&a" + player.getWorld().getTime() + " ticks&r)"));
            } else {
                sender.sendMessage("This command can only be run by a player.");
            }
            return true;
        }
        return false;
    }
}
