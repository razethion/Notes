package pw.zeth.ra.notes.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import pw.zeth.ra.notes.Main;

public class Delnote implements CommandExecutor {
	Main plugin;

	public Delnote(Main instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to use this command");
			return true;
		}

		Player player = (Player) sender;

		if (args.length == 0) {

			player.sendMessage(ChatColor.RED + "You need to pick a player, dumby!");
			return true;

		} else if (args.length == 1) {

			player.sendMessage(ChatColor.RED + "Please specify the date/time format of the note to remove.");
			return true;

		} else if (args.length >= 2) {

			String notee = args[0];
			String time = args[1];

			@SuppressWarnings("deprecation")
			String uuid = Bukkit.getOfflinePlayer(notee).getUniqueId().toString();

			if (plugin.getConfig().isSet("notes." + uuid + "." + time)) {
				plugin.getConfig().set("notes." + uuid + "." + time + ".deleted", "1");
				plugin.getConfig().set("notes." + uuid + "." + time + ".deleted-by", player.getName().toString());
				plugin.saveConfig();

				ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
				String command1 = "a &c" + player.toString() + "&7has deleted the note for &c" + notee + " &7set at &c" + time;
				Bukkit.dispatchCommand(console, command1);

				player.sendMessage(ChatColor.GRAY + "Deleted note for " + ChatColor.RED + notee + ChatColor.GRAY + " at " + ChatColor.RED + time);
				return true;

			} else {
				player.sendMessage(ChatColor.RED + "Could not find that note.");
				return true;
			}

		}

		return true;

	}

}
