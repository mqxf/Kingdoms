package com.kingdomsofargus.kingdoms.kingdom.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

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

public class KingdomJoinArgument extends CommandArgument {

	private final Kingdoms plugin;
	
	public KingdomJoinArgument(Kingdoms plugin) {
        super("join", "Join a kingdom", new String[]{"j"});
		this.plugin = plugin;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName() + " <faction>";
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
        	
        Player player = (Player) sender;
        String name = args[1];

        if (!KingdomManager.kingdomExists(name)) {
        	player.sendMessage(ChatColor.RED + "That kingdom does not exist!");
        	return true;
        	}
        
        if (KingdomPlayer.getKingdom(player.getUniqueId()).equals("NONE")) {
        	player.sendMessage(ChatColor.RED + "Successfully joined the kingdom.");
        	KingdomPlayer.setKingdom(player.getUniqueId(), name);
        } else {
        	player.sendMessage(ChatColor.RED + "You are already in a kingdom!");
        	return true;
        }
        
		return false;
	}

}
