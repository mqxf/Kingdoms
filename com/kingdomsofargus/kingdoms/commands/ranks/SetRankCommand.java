package com.kingdomsofargus.kingdoms.commands.ranks;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.utils.Utils;

import net.md_5.bungee.api.ChatColor;

public class SetRankCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("commands.rank.set")) {
				if (args.length == 2) {
					
					if (Bukkit.getPlayer(args[0]) != null) {
						Player target = Bukkit.getPlayer(args[0]);
						
						switch(args[1]) {
						case "Default":
						case "Member":
							KingdomPlayer.setRank(target.getUniqueId(), "Member");
							break;
						case "Helper":
							KingdomPlayer.setRank(target.getUniqueId(), "Helper");
							break;
						case "JrMod":
							KingdomPlayer.setRank(target.getUniqueId(), "JrMod");
							break;
						case "Moderator":
						case "Mod":
							KingdomPlayer.setRank(target.getUniqueId(), "Moderator");
							break;
						case "Builder":
							KingdomPlayer.setRank(target.getUniqueId(), "Builder");
							break;
						case "Developer":
						case "Dev":
							KingdomPlayer.setRank(target.getUniqueId(), "Developer");
							break;
						case "Admin":
							KingdomPlayer.setRank(target.getUniqueId(), "Admin");
							break;
						case "Owner":
							KingdomPlayer.setRank(target.getUniqueId(), "Owner");
							break;
						case "Test":
							KingdomPlayer.setRank(target.getUniqueId(), "Test");
							break;
						default:
							player.sendMessage(Utils.chat("&cInvalid rank"));
							break;
						}
						player.sendMessage(Utils.chat("&eSuccessfully set &6&l" + target.getName() + "'s &erank to &6&l" + args[1]));
					} else {
						player.sendMessage(Utils.chat("&cThat player is not online or doesn't exist"));
					}
					
				} else {
					player.sendMessage(Utils.chat("&cUsage: /setrank <player> <rank>"));
				}
			}
			else {
				player.sendMessage(Utils.chat("&cNo Permission."));
			}
		}
		else {
			if (args.length == 2) {
				
				if (Bukkit.getPlayer(args[0]) != null) {
					Player target = Bukkit.getPlayer(args[0]);
					
					//the Rank.setRank() will put the rank into SQL
					//the main.rank.put() will make it so that we can access the rank from this plugin
					switch(args[1]) {
					case "Default":
					case "Member":
						KingdomPlayer.setRank(target.getUniqueId(), "Member");
						break;
					case "Helper":
						KingdomPlayer.setRank(target.getUniqueId(), "Helper");
						break;
					case "JrMod":
						KingdomPlayer.setRank(target.getUniqueId(), "JrMod");
						break;
					case "Moderator":
					case "Mod":
						KingdomPlayer.setRank(target.getUniqueId(), "Moderator");
						break;
					case "Builder":
						KingdomPlayer.setRank(target.getUniqueId(), "Builder");
						break;
					case "Developer":
					case "Dev":
						KingdomPlayer.setRank(target.getUniqueId(), "Developer");
						break;
					case "Admin":
						KingdomPlayer.setRank(target.getUniqueId(), "Admin");
						break;
					case "Owner":
						KingdomPlayer.setRank(target.getUniqueId(), "Owner");
						break;
					default:
						sender.sendMessage(Utils.chat("&cInvalid rank"));
						break;
					}
					
				} else {
					sender.sendMessage(Utils.chat("&cThat player is not online or doesn't exist"));
				}
				
			}
		}
		return false;
	}

}
