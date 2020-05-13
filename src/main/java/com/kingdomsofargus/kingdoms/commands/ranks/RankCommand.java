package com.kingdomsofargus.kingdoms.commands.ranks;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RankCommand implements CommandExecutor, TabCompleter
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("kingdoms.admin")) {
			if (command.getName().equalsIgnoreCase("rank")) {
				if (args.length == 0) {
					sender.sendMessage(Color.color("&7(&cKingdoms&7) &c/rank help"));
					return false;
				}

				/**
				 * @PARAM /rank <COMMAND>
				 */

				String subcommand = args[0];

				if (subcommand.equalsIgnoreCase("help")) {
					for (String message : Kingdoms.getCore().getMessageFile().getStringList("rank-help")) {
						sender.sendMessage(Color.color(message));
					}
					return false;
				}



				Player player = Bukkit.getPlayer(args[1]);

				if (player == null && Kingdoms.getCore().getUserManager().getOfflineUUID(args[1]) == null) {
					sender.sendMessage(Kingdoms.getCore().getMessageFile().getString(Color.color("player-offline")));
					return false;
				}


				/**
				 * @PlaceholderSystem
				 * @USAGE: {PLAYER}, {RANK}
				 */

				Map<String, String> values = new HashMap<>();

				values.put("PLAYER", player.getDisplayName());
				values.put("RANK", args[2]);
				switch (subcommand) {
					case "set":
						Kingdoms.getCore().getUserManager().getUser(player).setRank(args[2]);
						sender.sendMessage(Color.color("&fYou have set &4" + player.getName() + "'s &frank to &4" + args[2]));
						break;
					case "reset":
						Kingdoms.getCore().getUserManager().getUser(player).setRank("default"); //TODO May need to be update
						sender.sendMessage(Color.color("&fYou have reset &4" + player.getName() + "'s &frank"));
						break;
					case "list":
						// TODO
						break;
					case "reload":
						// TODO
						break;
				}
			}
			}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if(command.getName().equalsIgnoreCase("rankset") && args.length >= 2) {
			if(sender instanceof Player) {
				return Kingdoms.getCore().getIdToRank().keySet().stream().collect(Collectors.toList());
			}
		}
		return null;
	}

}