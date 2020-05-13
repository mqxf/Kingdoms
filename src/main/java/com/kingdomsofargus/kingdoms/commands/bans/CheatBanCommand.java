package com.kingdomsofargus.kingdoms.commands.bans;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.gui.bans.CheatBanGUI;
import com.kingdomsofargus.kingdoms.utils.Utils;

public class CheatBanCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("commands.ban")) {
				if (args.length == 1) {
					Player p = Bukkit.getPlayer(args[0]);
					Kingdoms.getCore().BANUI.put(player, p);
					CheatBanGUI.applyBanUI(p, player, 1);
				}
				else {
					player.sendMessage(Utils.chat("&cUsage: /cheatban <player>"));
				}
			}
		}
		else {
			if (args.length == 3) {
				Player player = Bukkit.getPlayer(args[0]);
				if (args[2].length() == 2) {
					Kingdoms.getCore().banPlayer(args[2], player, Integer.parseInt(args[1]));
				}
			}
		}
		return false;
	}

}
