package com.kingdomsofargus.kingdoms.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SBToggleCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			/**
			if (SBProvider.hasScoreboard.contains(player.getUniqueId())) {
				SBProvider.hasScoreboard.remove(player.getUniqueId());
				player.sendMessage(Utils.chat("&cScoreboard disabled"));
			} else {
				SBProvider.hasScoreboard.add(player.getUniqueId());
				player.sendMessage(Utils.chat("&aScoreboard enabled"));
			}
			 */ //TODO
		} else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You cannot execute this command as console");
		}
		return false;
	}

}
