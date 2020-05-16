package com.kingdomsofargus.kingdoms.commands.staff;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.utils.Utils;

import net.md_5.bungee.api.ChatColor;

public class HealCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You cannot execute this command as console");
		} else {
			Player player = (Player) sender;
			
			if (!player.hasPermission("commands.heal")) {
				player.sendMessage(ChatColor.RED + "No Permission.");
			} else {
				if (args.length == 0) {
					player.setHealth(player.getMaxHealth());
					player.setFoodLevel(20);
					player.sendMessage(Utils.chat("&eYou have been successfully healed"));
				} else {
					if (Bukkit.getPlayer(args[0]) != null) {
						Player target = Bukkit.getPlayer(args[0]);
						
						target.setHealth(target.getMaxHealth());
						target.setFoodLevel(20);
						player.sendMessage(Utils.chat("&eSuccessfully healed &6&l" + target.getName()));
					}
				}
			}
		}
		
		return false;
	}

}
