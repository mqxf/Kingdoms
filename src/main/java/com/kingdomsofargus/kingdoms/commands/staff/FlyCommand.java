package com.kingdomsofargus.kingdoms.commands.staff;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.utils.Utils;

import net.md_5.bungee.api.ChatColor;

public class FlyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You cannot execute this command as console");
		} else {
			Player player = (Player) sender;
			
			if (!player.hasPermission("commands.fly")) {
				player.sendMessage(ChatColor.RED + "No Permission.");
			} else {
				if (player.getAllowFlight() != true) {
					player.setAllowFlight(true);
					player.sendMessage(Utils.chat("&aYou have enabled &7FLIGHT"));
				} else {
					player.setAllowFlight(false);
					player.sendMessage(Utils.chat("&cYou have disabled &7FLIGHT"));
				}
			}
		}
		
		return false;
	}

}
