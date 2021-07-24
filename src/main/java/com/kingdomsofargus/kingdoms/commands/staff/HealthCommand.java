package com.kingdomsofargus.kingdoms.commands.staff;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealthCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("commands.health")) {
				if (args.length == 2) {
					Player p = Bukkit.getPlayer(args[0]);
					double health = Double.parseDouble(args[1]) * 2;
					p.setMaxHealth(health);
					p.setHealth(health);
				}
			}
		} 
		return false;
	}

}
