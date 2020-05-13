package com.kingdomsofargus.kingdoms.commands.ranks;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.player.KingdomPlayer;

import net.md_5.bungee.api.ChatColor;

public class GenderCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			if (args.length == 0) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Usage: /gender <player>");
				return true;
			}
			
			if (Bukkit.getPlayer(args[0]) != null) {
				Player target = Bukkit.getPlayer(args[0]);
				KingdomPlayer.getGender(target.getUniqueId());
			} else {
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "That player is not online.");
			}
		} else {
			Player player = (Player) sender;
			
			if (args.length == 0) {
				player.sendMessage(ChatColor.RED + "Usage: /gender <player>");
				return true;
			}
			
			if (Bukkit.getPlayer(args[0]) != null) {
				Player target = Bukkit.getPlayer(args[0]);
				KingdomPlayer.getGender(target.getUniqueId());
			} else {
				player.sendMessage(ChatColor.RED + "That player is not online.");
			}
		}
		
		return false;
	}

}
