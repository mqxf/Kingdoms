package com.kingdomsofargus.kingdoms.commands.ranks;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.player.KingdomRanks;
import com.kingdomsofargus.kingdoms.utils.Utils;

public class CreateRankCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			
		}
		else {
			Player player = (Player) sender;
			if (player.hasPermission("commands.ranks.create")) {
				if (args.length == 2) {
					String name = args[0];
					if (Kingdoms.getInstance().ranks.contains(name)) {
						player.sendMessage(Utils.chat("&cRank already exists!"));
					}
					else {
						KingdomRanks.createRank(args[0], args[1]);
						Kingdoms.getInstance().ranks.add(name);
						Kingdoms.getInstance().getConfig().set("ranks." + args[0] + ".prefix", args[1]);
						List<String> list = Kingdoms.getInstance().getConfig().getStringList("ranks." + args[0] + ".permissions");
						list.add("default-permission");
						Kingdoms.getInstance().getConfig().set("ranks." + args[0] + ".permissions", list);
						Kingdoms.getInstance().saveConfig();
					}
				}
				else {
					player.sendMessage(Utils.chat("&cUsage: /createrank <name> <prefix>"));
				}
			}
			else {
				player.sendMessage(Utils.chat("&c&lInsufficient permission."));
			}
		}
		return false;
	}

}
