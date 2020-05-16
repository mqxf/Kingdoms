package com.kingdomsofargus.kingdoms.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.scoreboard.AssembleBoard;
import com.kingdomsofargus.kingdoms.utils.Utils;

import net.md_5.bungee.api.ChatColor;

public class SBToggleCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (AssembleBoard.hasScoreboard.contains(player.getUniqueId())) {
				AssembleBoard.hasScoreboard.remove(player.getUniqueId());
				player.sendMessage(Utils.chat("&cScoreboard disabled"));
			} else {
				AssembleBoard.hasScoreboard.add(player.getUniqueId());
				player.sendMessage(Utils.chat("&aScoreboard enabled"));
			}
		} else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You cannot execute this command as console");
		}
		return false;
	}

}
