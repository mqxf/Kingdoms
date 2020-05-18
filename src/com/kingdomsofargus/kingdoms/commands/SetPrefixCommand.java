package com.kingdomsofargus.kingdoms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.player.KingdomRanks;
import com.kingdomsofargus.kingdoms.utils.Utils;

import net.md_5.bungee.api.ChatColor;

public class SetPrefixCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			
		} else {
			Player player = (Player) sender;
			
			if (!player.hasPermission("commands.rank.prefix.set")) {
				player.sendMessage(ChatColor.RED + "No Permission");
			} else {
				if (args.length < 2) {
					player.sendMessage(ChatColor.RED + "Usage: /setprefix <rank> <prefix>");
				} else {	
					player.sendMessage(ChatColor.YELLOW + "Successfully changed " + ChatColor.GOLD.toString() + ChatColor.BOLD + args[0] + ChatColor.YELLOW
							+ "'s prefix to " + ChatColor.GOLD.toString() + ChatColor.BOLD + args[1]);
					switch(args[0]) {
					case "Default":
					case "Member":
						KingdomRanks.setPrefix(args[1], "Member");
						break;
					case "Helper":
						KingdomRanks.setPrefix(args[1], "Helper");
						break;
					case "JrMod":
						KingdomRanks.setPrefix(args[1], "JrMod");
						break;
					case "Moderator":
					case "Mod":
						KingdomRanks.setPrefix(args[1], "Moderator");
						break;
					case "Builder":
						KingdomRanks.setPrefix(args[1], "Builder");
						break;
					case "Developer":
					case "Dev":
						KingdomRanks.setPrefix(args[1], "Developer");
						break;
					case "Admin":
						KingdomRanks.setPrefix(args[1], "Admin");
						break;
					case "Owner":
						KingdomRanks.setPrefix(args[1], "Owner");
						break;
					default:
						player.sendMessage(Utils.chat("&cInvalid rank"));
						break;
					}
				} 
			}
		}
		
		return false;
	}

}
