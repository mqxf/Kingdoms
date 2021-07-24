package com.kingdomsofargus.kingdoms.commands.disguise;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.gui.DisguiseGUI;
import com.kingdomsofargus.kingdoms.sql.Methods;

import net.md_5.bungee.api.ChatColor;

public class DisguiseCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
			return true;
		}
		
		Player player = (Player) sender;
		
		if (!player.hasPermission("command.nickname.use")) {
			player.sendMessage(ChatColor.RED + "No Permission");
			return true;
		}
		
		if (args.length == 0) {
			player.sendMessage(ChatColor.RED + "Usage: /disguise <playerName>");
			return true;
		}
		
		Player tplayer = Bukkit.getPlayer(args[0]);
		
		if (Methods.playerExists(tplayer.getUniqueId())) {
			player.sendMessage(ChatColor.RED + "they are already a player in our database.");
		} else {
			DisguiseGUI.apply(player);
		}
		
		
		return false;
	}

}
