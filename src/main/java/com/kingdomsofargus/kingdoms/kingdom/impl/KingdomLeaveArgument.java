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

public class KingdomLeaveArgument extends CommandArgument {

	private final Kingdoms plugin;
	
	public KingdomLeaveArgument(Kingdoms plugin) {
        super("leave", "Leave a kingdom");
		this.plugin = plugin;
	}

	@Override
	public String getUsage(String label) {
		return '/' + label + ' ' + getName();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You cannot execute this command as console.");
			return true;
		}
		
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: " + getUsage(label));
            return true;
        }
        
        
        Player player = (Player) sender;
        String kingdom = KingdomPlayer.getKingdom(player.getUniqueId());
        
        if (kingdom.equals("NONE")) {
        	sender.sendMessage(ChatColor.RED + "You aren't in a kingdom!");
        	return true;
        }
        if (KingdomPlayer.getClass(player.getUniqueId()).equals("King")
        		|| KingdomPlayer.getClass(player.getUniqueId()).equals("Queen")) {
        	player.sendMessage(Utils.chat("&eYou are a leader, do /k disband to disband your kingdom."));
        	} else {
        		KingdomPlayer.setClass(player.getUniqueId(), "Wanderer");
        		KingdomPlayer.setKingdom(player.getUniqueId(), "NONE");
        		player.sendMessage(ChatColor.YELLOW + "Successfully left the kingdom");
        		
        		if (Bukkit.getPlayer(KingdomManager.getLeader(kingdom)) != null) {
        			Player leader = Bukkit.getPlayer(KingdomManager.getLeader(kingdom));
        			leader.sendMessage(ChatColor.RED + player.getName() + " left your kingdom!");
        	}
        }        
		return false;
	}

}
