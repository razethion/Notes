package pw.zeth.ra.notes.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import pw.zeth.ra.notes.Main;

public class Notes implements CommandExecutor {
	Main plugin;

	public Notes(Main instance) {
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

		} else {

			//THIS
			String notee = args[0];

			@SuppressWarnings("deprecation")
			String uuid = Bukkit.getOfflinePlayer(notee).getUniqueId().toString();

			@SuppressWarnings("unused")
			boolean viewdeleted;
			if(player.hasPermission("notes.viewdeleted")){
				viewdeleted = true;
			} else {
				viewdeleted = false;
			}

			if (plugin.getConfig().isSet("notes." + uuid)) {

				player.sendMessage(ChatColor.WHITE + "=====" + ChatColor.GOLD + "Displaying " + ChatColor.YELLOW + notee + "'s" + ChatColor.GOLD + " notes" + ChatColor.WHITE + "=====");

				for (String notedtime : plugin.getConfig().getConfigurationSection("notes." + uuid).getKeys(false)) {

					if (viewdeleted = false) {

						if (!plugin.getConfig().isSet("notes." + uuid + "." +  notedtime + ".deleted") && !plugin.getConfig().isSet("notes." + uuid + "." +  notedtime + ".old")) {

							String notemessage = plugin.getConfig().getString("notes." + uuid + "." + notedtime + ".note");
							String notedby = plugin.getConfig().getString("notes." + uuid + "." + notedtime + ".noter");

							ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
							String command1 = "tellraw " + player.getName().toString() + " [\"\",{\"text\":\"" + notedtime + " \",\"color\":\"red\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"-\",\"color\":\"gray\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\" \",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"" + notemessage + "\",\"color\":\"white\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"by\",\"color\":\"gray\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\" \",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"" + notedby + "\",\"color\":\"red\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}}]";
							Bukkit.dispatchCommand(console, command1);

						}
					} else {

						if (plugin.getConfig().isSet("notes." + uuid + "." +  notedtime + ".deleted")) {
							String deletedby = plugin.getConfig().getString("notes." + uuid + "." + notedtime + ".deleted-by").toString();
							player.sendMessage(deletedby);
							String isdeleted = " - DELETED BY " + deletedby + " -";

							String notemessage = plugin.getConfig().getString("notes." + uuid + "." + notedtime + ".note");
							String notedby = plugin.getConfig().getString("notes." + uuid + "." + notedtime + ".noter");

							ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
							String command1 = "tellraw " + player.getName().toString() + " [\"\",{\"text\":\"" + notedtime + isdeleted + " \",\"color\":\"red\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"-\",\"color\":\"gray\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\" \",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"" + notemessage + "\",\"color\":\"white\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"by\",\"color\":\"gray\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\" \",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"" + notedby + "\",\"color\":\"red\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}}]";
							Bukkit.dispatchCommand(console, command1);
						} else if (plugin.getConfig().isSet("notes." + uuid + "." +  notedtime + ".old")) {
							String isold = " - OLD - ";

							String notemessage = plugin.getConfig().getString("notes." + uuid + "." + notedtime + ".note");
							String notedby = plugin.getConfig().getString("notes." + uuid + "." + notedtime + ".noter");

							ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
							String command1 = "tellraw " + player.getName().toString() + " [\"\",{\"text\":\"" + notedtime + isold + " \",\"color\":\"red\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"-\",\"color\":\"gray\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\" \",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"" + notemessage + "\",\"color\":\"white\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"by\",\"color\":\"gray\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\" \",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"" + notedby + "\",\"color\":\"red\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}}]";
							Bukkit.dispatchCommand(console, command1);
						} else {
							String notemessage = plugin.getConfig().getString("notes." + uuid + "." + notedtime + ".note");
							String notedby = plugin.getConfig().getString("notes." + uuid + "." + notedtime + ".noter");

							ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
							String command1 = "tellraw " + player.getName().toString() + " [\"\",{\"text\":\"" + notedtime + " \",\"color\":\"red\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"-\",\"color\":\"gray\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\" \",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"" + notemessage + "\",\"color\":\"white\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"by\",\"color\":\"gray\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\" \",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}},{\"text\":\"" + notedby + "\",\"color\":\"red\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/delnote " + notee + " " + notedtime + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"Click to delete\"}}]";
							Bukkit.dispatchCommand(console, command1);
						}
					}
				}

			} else {
				player.sendMessage(ChatColor.RED + "This player has never been noted.");
				return true;
			}

		}

		return true;

	}

}
