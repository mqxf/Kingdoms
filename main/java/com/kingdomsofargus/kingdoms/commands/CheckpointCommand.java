package com.kingdomsofargus.kingdoms.commands;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.utils.Utils;

import net.md_5.bungee.api.ChatColor;

public class CheckpointCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You must be a player to execute this command");
			return true;
		}
		
		Player player = (Player) sender;
		if (!player.hasPermission("command.checkpoint")) {
			player.sendMessage(ChatColor.RED + "No Permission");
			return true;
		}
		
		if (args.length == 0 || args.length == 1) {
			player.sendMessage(ChatColor.RED + "Usage: /checkpoint set <number>");
			return true;
		}
		
		if (!isStringInt(args[1])) {
			player.sendMessage(ChatColor.RED + "That is not an integer.");
		} else {
			int value = Integer.valueOf(args[1]);
			
			switch (value) {
				case 1:
					setCheckpoint(1, player.getLocation());
					player.sendMessage(Utils.chat("&7(1) &eSuccessfully set checkpoint location"));
					break;
				case 2:
					setCheckpoint(2, player.getLocation());
					player.sendMessage(Utils.chat("&7(2) &eSuccessfully set checkpoint location"));
					break;
				case 3:
					setCheckpoint(3, player.getLocation());
					player.sendMessage(Utils.chat("&7(3) &eSuccessfully set checkpoint location"));
					break;
				case 4:
					setCheckpoint(4, player.getLocation());
					player.sendMessage(Utils.chat("&7(4) &eSuccessfully set checkpoint location"));
					break;
				case 5:
					setCheckpoint(5, player.getLocation());
					player.sendMessage(Utils.chat("&7(5) &eSuccessfully set checkpoint location"));
					break;
				case 6:
					setCheckpoint(6, player.getLocation());
					player.sendMessage(Utils.chat("&7(6) &eSuccessfully set checkpoint location"));
					break;
				case 7:
					setCheckpoint(7, player.getLocation());
					player.sendMessage(Utils.chat("&7(7) &eSuccessfully set checkpoint location"));
					break;
				case 8:
					setCheckpoint(8, player.getLocation());
					player.sendMessage(Utils.chat("&7(8) &eSuccessfully set checkpoint location"));
					break;
				case 9:
					setCheckpoint(9, player.getLocation());
					player.sendMessage(Utils.chat("&7(9) &eSuccessfully set checkpoint location"));
					break;
				case 10:
					setCheckpoint(10, player.getLocation());
					player.sendMessage(Utils.chat("&7(10) &eSuccessfully set checkpoint location"));
					break;
				case 11:
					setCheckpoint(11, player.getLocation());
					player.sendMessage(Utils.chat("&7(11) &eSuccessfully set checkpoint location"));
					break;
				case 12:
					setCheckpoint(12, player.getLocation());
					player.sendMessage(Utils.chat("&7(12) &eSuccessfully set checkpoint location"));
					break;
				case 13:
					setCheckpoint(13, player.getLocation());
					player.sendMessage(Utils.chat("&7(13) &eSuccessfully set checkpoint location"));
					break;
				default:
					player.sendMessage(ChatColor.RED + "Checkpoints are from 1 - 13");
			}
		}
		
		return false;
	}

	public boolean isStringInt(String s) {
	    try {
	        DecimalFormat df = new DecimalFormat("#.00");
	        return true;
	    } catch (NumberFormatException ex) {
	        return false;
	    }
	}
	
	public void setCheckpoint(Integer integer, Location location) {
		FileConfiguration config = Kingdoms.getInstance().getConfig();
		
		config.set("checkpoints." + integer + ".world", location.getWorld().getName());
		config.set("checkpoints." + integer + ".x", location.getX());
		config.set("checkpoints." + integer + ".y", location.getY());
		config.set("checkpoints." + integer + ".z", location.getZ());
		Kingdoms.getInstance().saveConfig();
	}
	public static Location getCheckpoint(Integer integer) {
		FileConfiguration config = Kingdoms.getInstance().getConfig();
		
		String world = config.getString("checkpoints." + integer + ".world");
		int x = config.getInt("checkpoints." + integer + ".x");
		int y = config.getInt("checkpoints." + integer + ".y");
		int z = config.getInt("checkpoints." + integer + ".z");
		
		Location location = new Location(Bukkit.getWorld(world), x, y, z);
		
		return location;
	}
}
