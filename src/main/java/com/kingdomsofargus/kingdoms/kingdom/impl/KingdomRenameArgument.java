package com.kingdomsofargus.kingdoms.kingdom.impl;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.kingdomsofargus.kingdoms.Kingdoms;
import com.kingdomsofargus.kingdoms.kingdom.KingdomExecutor;
import com.kingdomsofargus.kingdoms.kingdom.KingdomManager;
import com.kingdomsofargus.kingdoms.player.KingdomPlayer;
import com.kingdomsofargus.kingdoms.utils.Utils;
import com.kingdomsofargus.kingdoms.utils.command.CommandArgument;

import net.md_5.bungee.api.ChatColor;

public class KingdomRenameArgument extends CommandArgument {

	private final Kingdoms plugin;
	
	public KingdomRenameArgument(Kingdoms plugin) {
        super("rename", "Rename your kingdom");
		this.plugin = plugin;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName() + " <newName>";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You cannot execute this command as console.");
			return true;
		}
		
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: " + getUsage(label));
            return true;
        }
        
        String name = args[1];
        
        if (name.length() < 4) {
        	sender.sendMessage(ChatColor.RED + "Kingdom names must have at least 4 characters.");
        	return true;
        }
        
        if (name.length() > 16) {
        	sender.sendMessage(ChatColor.RED + "Kingdom names must cannot be longer than 16 characters.");
        	return true;
        }
        
        if (KingdomManager.kingdomExists(name)) {
        	sender.sendMessage(ChatColor.RED + "That name is already taken!");
        	return true;
        }
        
        Player player = (Player) sender;
        String kingdom = KingdomPlayer.getKingdom(player.getUniqueId());
        if (kingdom.equals("NONE")) {
        	sender.sendMessage(ChatColor.RED + "You aren't in a kingdom!");
        	return true;
        } else {
        
        	if (KingdomPlayer.getClass(player.getUniqueId()).equals("King") || KingdomPlayer.getClass(player.getUniqueId()).equals("Queen")) {
        		KingdomManager.renameKingdom(kingdom, name);
        		KingdomPlayer.setKingdom(player.getUniqueId(), name);
        		player.sendMessage(ChatColor.YELLOW + "Successfully renamed kingdom to " + ChatColor.GRAY + name);
        	} else {
        		player.sendMessage(ChatColor.RED + "You are not high enough in the kingdom to do this.");
        	}
        	
        
        }        
		return false;
	}

}
