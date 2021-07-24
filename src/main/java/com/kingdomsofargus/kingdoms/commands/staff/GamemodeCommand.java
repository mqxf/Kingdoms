package com.kingdomsofargus.kingdoms.commands.staff;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.utils.Utils;
import com.sk89q.worldedit.antlr.ExpressionParser.CaseContext;

import net.md_5.bungee.api.ChatColor;

public class GamemodeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You cannot execute this command as console");
		} else {
			Player player = (Player) sender;
			
			if (!player.hasPermission("commands.gamemode")) {
				player.sendMessage(ChatColor.RED + "No Permission.");
			} else {
				if (args.length == 0) {
					player.sendMessage(ChatColor.RED + "Usage: /gm <c:s:a>");
				} else {
					switch (args[0]) {
					case "1":
					case "c":
					case "creative":
						player.setGameMode(GameMode.CREATIVE);
						player.sendMessage(Utils.chat("&eSuccessfully switched your gamemode to &7CREATIVE"));
						break;
					case "0":
					case "s":
					case "survival":
						player.setGameMode(GameMode.SURVIVAL);
						player.sendMessage(Utils.chat("&eSuccessfully switched your gamemode to &7SURVIVAL"));
						break;
					case "spectator":
					case "3":
					case "sp":
						player.setGameMode(GameMode.SPECTATOR);
						player.sendMessage(Utils.chat("&eSuccessfully switched your gamemode to &7SPECTATOR"));
						break;
					case "2":
					case "a":
					case "adventure":
						player.setGameMode(GameMode.ADVENTURE);
						player.sendMessage(Utils.chat("&eSuccessfully switched your gamemode to &7ADVENTURE"));
						break;
					default:
						player.setGameMode(GameMode.SURVIVAL);
						player.sendMessage(Utils.chat("&eSuccessfully switched your gamemode to &7SURVIVAL"));
						break;
					}
				}
			}
		}
		
		return false;
	}

}
